package com.tutorials180.classprojecte6.CustomArrayAdapterWork

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tutorials180.classprojecte6.R

class CustomArrayAdapter (context:Context,player: List<Player>)
    :ArrayAdapter<Player>(context, R.layout.single_row,player)
{
    var playerlist=player
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var singleRow=LayoutInflater.from(context).inflate(R.layout.single_row,null,true)

        var nameTV=singleRow.findViewById<TextView>(R.id.sr_name_tv)
        var battingStyleTV=singleRow.findViewById<TextView>(R.id.sr_batting_style_tv)

        var imageV=singleRow.findViewById<ImageView>(R.id.sr_iv)
        nameTV.text=playerlist.get(position).getPlayerName()

        battingStyleTV.text=playerlist.get(position).getPlayerBattingStyle()

        imageV.setImageResource(playerlist.get(position).getPlayerImage())
        return singleRow
    }
}