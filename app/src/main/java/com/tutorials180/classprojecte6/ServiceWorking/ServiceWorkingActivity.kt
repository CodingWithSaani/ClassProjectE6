package com.tutorials180.classprojecte6.ServiceWorking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tutorials180.classprojecte6.R
import com.tutorials180.classprojecte6.databinding.ActivityServiceWorkingBinding

class ServiceWorkingActivity : AppCompatActivity()
{
    private lateinit var mSWBinder : ActivityServiceWorkingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSWBinder = ActivityServiceWorkingBinding.inflate(layoutInflater)

        setContentView(mSWBinder.root)
        mSWBinder.swStartBtn.setOnClickListener {
            startService(Intent(ServiceWorkingActivity@this,MyService::class.java))
        }

        mSWBinder.swStopBtn.setOnClickListener {
            stopService(Intent(ServiceWorkingActivity@this,MyService::class.java))
        }
    }
}






