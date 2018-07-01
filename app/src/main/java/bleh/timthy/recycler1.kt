package bleh.timthy

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.icu.util.ULocale.getCountry
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.inputcard.view.*
import java.nio.file.Files.size




class recycler1(list:ArrayList<ttableinput>, mContext:Context):RecyclerView.Adapter<recycler1.BeneficiaryViewHolder>() {


    internal var list:ArrayList<ttableinput>?=null
    internal var mContext:Context
    internal var mFilteredList:ArrayList<ttableinput>
   override fun getItemCount():Int {
       return list!!.size
   }


    init{
        this.list = list
        this.mContext = mContext
        this.mFilteredList = list
    }
    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):BeneficiaryViewHolder {
        val itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inputcard, parent, false)
        return BeneficiaryViewHolder(itemView)
    }
    override fun onBindViewHolder(holder:recycler1.BeneficiaryViewHolder, position:Int) {
        holder.mname!!.setText(list!!.get(position).getPeriod()!!.toString())

    }
    inner class BeneficiaryViewHolder(view:View):RecyclerView.ViewHolder(view) {

        var mname:TextView?=null

        init{
            mname = view.findViewById(R.id.pno) as TextView

        }
    }
}