package com.tutorials180.classprojecte6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.lang.Exception

class SecondPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page)

        receivingIntent()
    }

    private fun receivingIntent()
    {
        try
        {
           var name: String? =intent.getStringExtra("name")
           var rollNo:Int=intent.getIntExtra("rollNo",0)

           Toast.makeText(this,"Name is:${name} and roll" +
                   "no is:${rollNo}",Toast.LENGTH_LONG).show()
        }
        catch (objectEx:Exception)
        {
            Toast.makeText(this,"Logical Error:${objectEx.message.toString()}"
                ,Toast.LENGTH_LONG).show()
        }
    }


}