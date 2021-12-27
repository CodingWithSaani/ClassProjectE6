package com.tutorials180.classprojecte6.SimpleRoomImplementation

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tutorials180.classprojecte6.R

class RVCustomAdapter(val listStudent:List<Student>):RecyclerView.Adapter<RVCustomAdapter.RVViewHolder>()
{
    class RVViewHolder(singleRow:View):RecyclerView.ViewHolder(singleRow)
    {
        var studentIdTV=singleRow.findViewById(R.id.std_sr_id_tv) as TextView
        var studentNameTV=singleRow.findViewById(R.id.std_sr_name_tv) as TextView

        var studentEmailTV=singleRow.findViewById(R.id.std_sr_email_tv) as TextView
    }


    //1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val singleRowStudent=LayoutInflater.from(parent.context).inflate(R.layout.student_single_row,parent,false)
        return RVViewHolder(singleRowStudent)
    }

    //2
    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        holder.studentIdTV.text=listStudent[position].id.toString()
        holder.studentNameTV.text=listStudent[position].name

        holder.studentEmailTV.text=listStudent[position].email
    }

    //3
    override fun getItemCount(): Int {
       return listStudent.size
    }
}


/*
    1- Too many layout inflator objects
    2- Too many views objects
 */