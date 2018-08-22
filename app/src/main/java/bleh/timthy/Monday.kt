package bleh.timthy

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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monday)


        createLayout()

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
    }
}
