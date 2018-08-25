package bleh.timthy

import android.content.Intent
import android.graphics.Color.WHITE
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.DefaultItemAnimator
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_monday.*


class Monday : AppCompatActivity() {
    var recyl:RecyclerView?=null
    var recyadapter:recycler1?=null
    var day:String?=null
    var key:Int?=null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monday)
        var inten=intent
        key=inten.getIntExtra("key",1)
        getday()
        val db=TimmyDatabase(this)
        db.tday(day.toString())

        createLayout()

    }
    fun getday(){
        if(key==1){
            day="monday"
        }
        if(key==2){
            day="tuesday"
        }
        if(key==3){
            day="wednesday"
        }
        if(key==4){
            day="thursday"
        }
        if(key==5){
            day="friday"
        }
        if(key==6){
            day="saturday"
        }
        if(key==7){
            day="sunday"
        }
    }

    fun createLayout(){

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val pcount = Integer.parseInt(preferences.getString("nperiod", "0"))
        var spinner =Array<Spinner>(pcount,{j ->Spinner(this)})
        var db=TimmyDatabase(this)
        var list=db.fetch_subbg(this)
        val array_adapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var sppinner=Array(pcount,{i-> Spinner(this)})
        var i=0
        var p=1
        for(i in 0..spinner.lastIndex){
            val tv=TextView(this)
            tv.setText("PERIOD:"+p)
            tv.setTextColor(WHITE)
            tv.setTextSize(20.0F)
            tv.setPadding(2,8,2,8)
            spinner[i].adapter=array_adapter
           spinner[i].onItemSelectedListener= object: AdapterView.OnItemSelectedListener{
               override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                   Log.e("selectedbleh", spinner[i].getSelectedItem().toString())
               }

               override fun onNothingSelected(parent: AdapterView<*>?) {
                   TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
               }
           }

            llmonday.addView(tv)
            llmonday.addView(spinner[i])
            p++
        }
        fab.setOnClickListener{view ->
            var i=0
            val adb=TimmyDatabase(this)
            var sub:String?=null
            for (i in 0..spinner.lastIndex){
               sub= spinner[i].selectedItem.toString()
                adb.ttable(day!!,sub)

            }
            changeactivty()
        }
    }
    fun changeactivty(){
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        var day=BooleanArray(8)
        day[1]= preferences.getBoolean("day1",false)
        day[2]= preferences.getBoolean("day2",false)
        day[3]= preferences.getBoolean("day3",false)
        day[4]= preferences.getBoolean("day4",false)
        day[5]= preferences.getBoolean("day5",false)
        day[6]= preferences.getBoolean("day6",false)
        day[7]= preferences.getBoolean("day7",false)
        var i:Int?=key!!+1
        while(i!!<=7){
            if(day[i]==true){
                val intent=Intent(this,Monday::class.java)
                intent.putExtra("key",i)
                startActivity(intent)
                break
            }
            else{
                i++
            }
        }
        if (i==8){
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

}
