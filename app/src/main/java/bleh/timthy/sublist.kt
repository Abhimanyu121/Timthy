package bleh.timthy

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import kotlinx.android.synthetic.main.activity_sublist.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.security.AccessController.getContext


class sublist : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sublist)


      createtv()

    }

     fun createtv() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val scount = Integer.parseInt(preferences.getString("total_sub", "0"))
        xtitle.setText("Enter name of all the "+scount +" subjects")
        var tva:Array<AutoCompleteTextView> = Array(scount,{j -> AutoCompleteTextView(this)  })
        var i = 0
        while (i <scount) {
            var k=i
            tva!![i].setHint("Enter Subject number :"+(k+1))
            llsublist.addView(tva!![i])

            i++
        }
        val button=buttonsub
        button.setOnClickListener {view ->
            var i=0
            while(i<scount){
                var name=tva[i].getText().toString()
                val db=TimmyDatabase(this)
                db.add_Sub(name,this)
                i++
                Toast.makeText(this,"Done!",Toast.LENGTH_LONG)

            }
        }


    }
    fun change_activity(){
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        var d1= preferences.getBoolean("day1",false)
        var d2= preferences.getBoolean("day2",false)
        var d3= preferences.getBoolean("day3",false)
        var d4= preferences.getBoolean("day4",false)
        var d5= preferences.getBoolean("day5",false)
        var d6= preferences.getBoolean("day6",false)
        var d7= preferences.getBoolean("day7",false)


    }

}



