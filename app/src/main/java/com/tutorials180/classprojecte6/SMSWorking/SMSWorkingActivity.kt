package com.tutorials180.classprojecte6.SMSWorking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tutorials180.classprojecte6.R
import com.tutorials180.classprojecte6.databinding.ActivitySmsworkingBinding
import java.lang.Exception

class SMSWorkingActivity : AppCompatActivity()
{
    private lateinit var mSMSWorkingActivityBinding:ActivitySmsworkingBinding  //Declare

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mSMSWorkingActivityBinding= ActivitySmsworkingBinding.inflate(layoutInflater)  //Initialize

        setContentView(mSMSWorkingActivityBinding.root)
        mSMSWorkingActivityBinding.smsWorkingSendMessageBtn.setOnClickListener { sendSMS() }
    }

    private fun sendSMS()
    {
        try
        {

        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext,ex.message,Toast.LENGTH_LONG).show()
        }
    }
}