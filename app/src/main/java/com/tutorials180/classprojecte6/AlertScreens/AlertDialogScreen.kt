package com.tutorials180.classprojecte6.AlertScreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.tutorials180.classprojecte6.R

class AlertDialogScreen : AppCompatActivity() {

    private lateinit var alertDialogBuilder:AlertDialog.Builder
    private lateinit var alertDialog:AlertDialog

    private lateinit var alertDialogBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialog_screen)

        connectorXML()
    }

    private fun connectorXML()
    {
        alertDialogBtn=findViewById(R.id.show_alert_dialog_btn)
        alertDialogBtn.setOnClickListener {
            createAlertDialog()
        }
    }

    private fun createAlertDialog()
    {
        alertDialogBuilder=AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Test")

        alertDialogBuilder.setMessage("Testing the Alert Dialog")
        alertDialogBuilder.setCancelable(false)

        alertDialogBuilder.setPositiveButton("Close"){
            d,id->
            alertDialog.dismiss()
        }

        alertDialog=alertDialogBuilder.create()
        alertDialog.show()
    }














}