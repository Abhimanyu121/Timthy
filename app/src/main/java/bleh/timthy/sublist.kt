package bleh.timthy

import android.content.Context
import android.content.Intent
import android.graphics.Color.WHITE
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
            tva!![i].setHintTextColor(WHITE)
            llsublist.addView(tva!![i])

            i++
        }
        val button=buttonsub
        button.setOnClickListener {view ->
            var i=0
            val db=TimmyDatabase(this)
            db.add_Sub("free_period",this)
            while(i<scount){
                var name=tva[i].getText().toString().replace(" ","_",true).toLowerCase()
                db.add_Sub(name,this)
                i++
                Toast.makeText(this,"Done!",Toast.LENGTH_LONG)

            }
            change_activity()
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

        if(d1==true){
            val intent=Intent(this,Monday::class.java)
            intent.putExtra("day","monday")
            intent.putExtra("key",1)
            startActivity(intent)
        }
        else if(d2==true){
            val intent=Intent(this,Monday::class.java)
            intent.putExtra("day","tuesday")
            intent.putExtra("key",2)
            startActivity(intent)
        }
        else if(d3==true){
            val intent=Intent(this,Monday::class.java)
            intent.putExtra("day","wednesday")
            intent.putExtra("key",3)
            startActivity(intent)
        }
        else if(d4==true){
            val intent=Intent(this,Monday::class.java)
            intent.putExtra("day","thursday")
            intent.putExtra("key",4)
            startActivity(intent)
        }
        else if(d5==true){
            val intent=Intent(this,Monday::class.java)
            intent.putExtra("day","friday")
            intent.putExtra("key",5)
            startActivity(intent)
        }
        else if(d6==true){
            val intent=Intent(this,Monday::class.java)
            intent.putExtra("day","saturday")
            intent.putExtra("key",6)
            startActivity(intent)
        }
        else if(d7==true){
            val intent=Intent(this,Monday::class.java)
            intent.putExtra("day","sunday")
            intent.putExtra("key",7)
            startActivity(intent)
        }
        else{
            Toast.makeText(this,"you should have chosen some days,now deal with it until i add exception handling",Toast.LENGTH_SHORT)
        }


    }

}



