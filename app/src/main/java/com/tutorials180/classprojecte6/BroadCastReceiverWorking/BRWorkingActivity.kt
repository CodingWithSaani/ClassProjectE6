package com.tutorials180.classprojecte6.BroadCastReceiverWorking

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tutorials180.classprojecte6.R
import com.tutorials180.classprojecte6.databinding.ActivityBrworkingBinding

class BRWorkingActivity : AppCompatActivity()
{
    private lateinit var mBRBinder : ActivityBrworkingBinding
    //private lateinit var mWifiManager: WifiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBRBinder = ActivityBrworkingBinding.inflate(layoutInflater)
        setContentView(mBRBinder.root)

//        mBRBinder.brWorkingBtn.setOnClickListener {
//            changeWifi()
//        }
    }

//    private fun changeWifi() {
//        mWifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//        mWifiManager.isWifiEnabled = !mWifiManager.isWifiEnabled
//
//
//    }

    private val wifiReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent?) {
            when (intent?.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_UNKNOWN))
            {
                WifiManager.WIFI_STATE_ENABLED ->
                {
                    mBRBinder.brWorkingTv.text = "Wifi is enabled"
                }
                WifiManager.WIFI_STATE_DISABLED ->
                {
                    mBRBinder.brWorkingTv.text = "Wifi is disabled"
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        registerReceiver(wifiReceiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(wifiReceiver)
    }
}














