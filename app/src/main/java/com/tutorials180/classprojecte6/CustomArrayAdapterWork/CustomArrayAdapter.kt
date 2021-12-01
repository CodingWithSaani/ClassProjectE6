package com.tutorials180.classprojecte6.CustomArrayAdapterWork

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.tutorials180.classprojecte6.R

class CustomArrayAdapter (context:Context,player: List<Player>)
    :ArrayAdapter<Player>(context, R.layout.single_row,player)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        LayoutInflater.from(context).inflate(R.layout.single_row,null,true)
        return super.getView(position, convertView, parent)
    }
}