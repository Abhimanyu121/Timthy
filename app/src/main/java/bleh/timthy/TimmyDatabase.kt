package bleh.timthy

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.security.AccessController.getContext

class TimmyDatabase(context: Context):SQLiteOpenHelper(context, "TimmyTable.db", null, 1){
    internal var DBhelper:TimmyDatabase?=null
    internal var db:SQLiteDatabase?=null
    internal var upgrade = "drop table if exists beneficiary;"
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val upgrade = "drop table if exists sub_list;"
        if (db != null) {
            db.execSQL(upgrade)
            onCreate(db)
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        var table="create table sub_list(name text);"
        if (db != null) {
            db.execSQL(table)
        }
    }
    fun add_Sub(sub_name:String,context:Context){
        try{ val adb:SQLiteDatabase=this.writableDatabase
            var createTable="create table '"+sub_name+"'(date text,attend INTEGER,u_attend INTEGER);"
            adb.execSQL(createTable)
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

    fun fetch_subbg(context: Context):ArrayList<String>?{
        var columns=arrayOf("name")
        var retunlist: ArrayList<String>?=null
        var db=this.readableDatabase
        var cursor: Cursor =db.query("Sub_list",columns,null,null,null,null,null)
        if(cursor.moveToFirst()){
            do{
                var subname=cursor.getString(cursor.getColumnIndex("name"))
                retunlist!!.add(subname)
            }while(cursor.moveToNext())
            cursor.close()
            db.close()
        }
        return retunlist
        Toast.makeText(context,"Sub List fetched",Toast.LENGTH_SHORT).show()

    }
}