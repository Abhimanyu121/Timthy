package bleh.timthy

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Parcel
import android.os.Parcelable
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import java.security.AccessController.getContext

class TimmyDatabase(context: Context):SQLiteOpenHelper(context, "TimmyTable.db", null, 1){
    internal var DBhelper:TimmyDatabase?=null
    internal var db:SQLiteDatabase?=null
    internal var upgrade = "drop table if exists beneficiary;"
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val upgrade = "drop table if exists sub_list;"
        val lupgrade = "drop table if exists sub_list;"
        if (db != null) {
            db.execSQL(upgrade)
            db.execSQL(lupgrade)
            onCreate(db)
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        var table="create table sub_list(name text);"
        var lab="create table lab_list(name text);"
        if (db != null) {
            db.execSQL(table)
            db.execSQL(lab)
        }
    }
    fun add_Sub(sub_name:String,context:Context){
        try{ val adb:SQLiteDatabase=this.writableDatabase
            var s=sub_name
            var createTable="create table '"+sub_name+"'(date text,attend INTEGER,u_attend INTEGER);"
            val session="create table '"+s.plus("s2")+"'(date text,attend INTEGER,u_attend INTEGER)"
            adb.execSQL(createTable)
            adb.execSQL(session)
           val db:SQLiteDatabase=this.writableDatabase
            var content: ContentValues= ContentValues()
            content.put("name",sub_name)
            db.insert("sub_list",null,content)
            db.close()
            Toast.makeText(context,"done",Toast.LENGTH_SHORT).show()
        }
        catch (e:Exception){
            Log.e("blehdb",e.message)}
    }
    fun add_lab(sub_name:String,context:Context){
        try{ val adb:SQLiteDatabase=this.writableDatabase
            var lab_name=sub_name.plus("_lab")
            var s=lab_name
            var createTable="create table '"+lab_name+"'(date text,attend INTEGER,u_attend INTEGER);"
            val session="create table '"+s.plus("s2")+"'(date text,attend INTEGER,u_attend INTEGER)"
            adb.execSQL(createTable)
            adb.execSQL(session)
            val db:SQLiteDatabase=this.writableDatabase
            var content: ContentValues= ContentValues()
            content.put("name",lab_name)
            db.insert("lab_list",null,content)
            db.close()
            Toast.makeText(context,"done",Toast.LENGTH_SHORT).show()
        }
        catch (e:Exception){
            Log.e("blehdb",e.message)}
    }
    fun tday(day:String){
        try {
            val adb: SQLiteDatabase = this.writableDatabase
            var table = "create table '" + day + "'(name text);"
            adb.execSQL(table)
        }
        catch (e:Exception){
            Log.e("belh",e.message)
        }
    }

    fun fetch_subbg(context: Context):Array<String>?{
        var columns=arrayOf("name")
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val pcount = Integer.parseInt(preferences.getString("total_sub", "0"))+1
        var retunlist=Array<String>(pcount,{i->String()})
        var db=this.readableDatabase

        var cursor: Cursor =db.query("Sub_list",columns,null,null,null,null,null)
        if(cursor.moveToFirst()){
            var l=0
            do{
                var subname=cursor.getString(cursor.getColumnIndex("name")).replace("_"," ",true).toUpperCase()
                Log.e("bleh",subname)
                retunlist[l]=subname
                Log.e("list",retunlist[l])
                l++

            }while(cursor.moveToNext())
            cursor.close()
            db.close()
         var i=0

        }
        Toast.makeText(context,"Sub List fetched",Toast.LENGTH_SHORT).show()
        return retunlist


    }
    fun fetch_lab(context: Context):Array<String>?{
        var columns=arrayOf("name")
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val pcount = Integer.parseInt(preferences.getString("lab", "0"))+1
        var retunlist=Array<String>(pcount,{i->String()})
        var db=this.readableDatabase

        var cursor: Cursor =db.query("Sub_list",columns,null,null,null,null,null)
        if(cursor.moveToFirst()){
            var l=0
            do{
                var subname=cursor.getString(cursor.getColumnIndex("name")).replace("_"," ",true).toUpperCase()
                Log.e("bleh",subname)
                retunlist[l]=subname
                Log.e("list",retunlist[l])
                l++

            }while(cursor.moveToNext())
            cursor.close()
            db.close()
            var i=0

        }
        Toast.makeText(context,"Sub List fetched",Toast.LENGTH_SHORT).show()
        return retunlist


    }

    fun ttable(day:String,name:String){
       try {
           val conv=name.replace(" ","_",true).toLowerCase()
           val adb: SQLiteDatabase = this.writableDatabase
           var content = ContentValues()
           content.put("conv", name)
           adb.insert(day, null, content)
           adb.close()
       }
       catch(e:Exception){
          Log.e("bleh",e.message)
           }


    }
}