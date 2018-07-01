package bleh.timthy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.DefaultItemAnimator
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_monday.*


class Monday : AppCompatActivity() {
    var recyl:RecyclerView?=null
    var recyadapter:recycler1?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monday)
        recyl=xrecym
       // callrecy()

    }
fun callrecy(){
    val preferences = PreferenceManager.getDefaultSharedPreferences(this)
    val pcount = Integer.parseInt(preferences.getString("nperiod", "0"))
    val periodcount= ArrayList<ttableinput>()
    var TableInput=ttableinput()
    var i=1
    while(i<pcount){
        TableInput.setPeriod(i)
        Log.e("bleh69",TableInput.getPeriod().toString())
        periodcount.add(TableInput)
        i++
    }

    recyadapter=recycler1(periodcount,this)
    val mLayoutManager = LinearLayoutManager(applicationContext)
    recyl!!.setLayoutManager(mLayoutManager);
    recyl!!.setItemAnimator(DefaultItemAnimator())
    recyl!!.setHasFixedSize(true);
    recyl!!.setAdapter(recyadapter)
}
    fun createLayout(){

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val pcount = Integer.parseInt(preferences.getString("nperiod", "0"))
        var spinner =Array<Spinner>(pcount,{j ->Spinner(this)})
        var db=TimmyDatabase(this)
        var list=db.fetch_subbg(this)
        val array_adapter=ArrayAdapter<String>(this,R.id.spinnerLayout,list)

    }
}
