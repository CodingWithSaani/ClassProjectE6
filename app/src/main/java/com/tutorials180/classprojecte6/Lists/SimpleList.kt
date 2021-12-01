package com.tutorials180.classprojecte6.Lists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.tutorials180.classprojecte6.R

class SimpleList : AppCompatActivity() {

    var daysList=arrayOf("Monday","Monday","Monday","Monday","Monday","Monday","Monday",
        "Monday","Monday","Monday","Monday","Monday","Monday","Monday","Monday","Monday",
        "Monday","Monday","Monday","Monday","Monday","Monday","Monday","Monday","Monday",
        "Monday","Monday","Monday","Monday",)

    lateinit var daysListView:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_list)

        daysListView=findViewById(R.id.days_list_view)
        var ourArrayAdapter=ArrayAdapter<String>(SimpleList@this,
        android.R.layout.simple_list_item_1,daysList)

        daysListView.adapter=ourArrayAdapter
    }
}