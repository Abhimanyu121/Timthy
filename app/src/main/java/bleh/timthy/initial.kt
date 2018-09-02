package bleh.timthy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_initial.*
import java.security.AccessController.getContext

class initial : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
        Log.e("bleh1","bleh")

    }
    fun bleh(v:View){
        Log.e("bleh2","bleh")
        var check=exceptionhandling()
        if (check==true)
        {writeprefs()}
    }
    fun writeprefs(){
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = preferences.edit()
        Log.e("bleh5","bleh")
        var nm=name.getText().toString()
        var bd1:Boolean=d1.isChecked
        var bd2=d2.isChecked
        var bd3=d3.isChecked
        var bd4=d4.isChecked
        var bd5=d5.isChecked
        var bd6=d6.isChecked
        var bd7=d7.isChecked
        var np=npd.getText().toString()
        var scount=tsub.getText().toString()
        var percentage=perct.getText().toString()
        editor.putString("name",nm)
        editor.putBoolean("day1",bd2)
        editor.putBoolean("day2",bd3)
        editor.putBoolean("day3",bd4)
        editor.putBoolean("day4",bd5)
        editor.putBoolean("day5",bd6)
        editor.putBoolean("day6",bd7)
        editor.putBoolean("day7",bd1)
        editor.putString("nperiod",np)
        editor.putString("total_sub",scount)
        editor.putString("percent",percentage)
        editor.putBoolean("status",true)
        Toast.makeText(this,"DONE",Toast.LENGTH_SHORT).show()
        if (editor.commit()==false)
        {
            Toast.makeText(this,"Something went Wrong Please Try Again!",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"DONE",Toast.LENGTH_SHORT).show()
            val intent= Intent(this,sublist::class.java)
            startActivity(intent)
        }
        Log.e("bleh6","bleh")

    }
    fun exceptionhandling(): Boolean {
        Log.e("bleh3","bleh")

        var n=select()
        Log.e("bleh7","bleh")
        if(name.getText().isNullOrEmpty()&& name.getText().isBlank())
        {  Toast.makeText(this,"Please enter your name!",Toast.LENGTH_LONG).show()
            Log.e("bleh8","bleh")
            return false

        }


       else if (perct.getText().isNullOrEmpty() && perct.getText().isBlank()){
            Toast.makeText(this,"Please enter minimum attendance required!",Toast.LENGTH_LONG).show()
            Log.e("bleh9","bleh")
            return false
        }

        else if(d1.isSelected()&& d2.isSelected()&& d3.isSelected()&& d4.isSelected()&& d5.isSelected()&& d6.isSelected()&& d7.isSelected())
        { Toast.makeText(this,"Please select at least one day",Toast.LENGTH_LONG).show()
            return false}
        else

        {
            Log.e("bleh11","bleh")
            return true}
    }
    fun select(): Int {
        Log.e("bleh4","bleh")
        var n:Int=0
        if(d1.isSelected()==true)
        {n+=1}
        if(d2.isSelected()==true)
        {n+=1}
        if(d3.isSelected()==true)
        {n+=1}
        if(d4.isSelected()==true)
        {n+=1}
        if(d5.isSelected()==true)
        {n+=1}
        if(d6.isSelected()==true)
        {n+=1}
        if(d7.isSelected()==true)
        {n+=1}
        return n
    }
}
