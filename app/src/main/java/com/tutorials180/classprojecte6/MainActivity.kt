package com.tutorials180.classprojecte6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.tutorials180.classprojecte6.AlertScreens.AlertDialogScreen
import com.tutorials180.classprojecte6.CustomArrayAdapterWork.CustomListActivity
import com.tutorials180.classprojecte6.FirebaseWorking.FbAuthWorkingActivity
import com.tutorials180.classprojecte6.FirebaseWorking.FirestoreWorkingActivity
import com.tutorials180.classprojecte6.LocationService.LocationServiceActivity
import com.tutorials180.classprojecte6.SimpleRoomImplementation.StudentRoomActivity

class MainActivity : AppCompatActivity() {
    var valueOne:Int=0
    var valueTwo:Int=0

    var varResult:Int=0
    private lateinit var firstValueET:EditText

    private lateinit var secondValueET:EditText
    private lateinit var calculateBtn: Button

    private lateinit var resultTV:TextView
    private lateinit var moveBtn:Button

    private lateinit var moveToAlertScreenBtn:Button
    private lateinit var moveToCustomListScreenBtn:Button

    private lateinit var moveToStudentRoomActivityBtn:Button
    private lateinit var moveToLocationServiceActivityBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connector()
    }

    private fun connector()
    {
        firstValueET=findViewById(R.id.first_value_et)

        secondValueET=findViewById(R.id.second_value_et)
        calculateBtn=findViewById(R.id.calculate_btn)

        resultTV=findViewById(R.id.result_tv)
        moveBtn=findViewById(R.id.move_btn)

        moveToAlertScreenBtn=findViewById(R.id.move_alert_page_btn)
        moveToCustomListScreenBtn=findViewById(R.id.moveToCustomListActivity)

        moveToStudentRoomActivityBtn=findViewById(R.id.moveToStudentRoomActivity)
        moveToLocationServiceActivityBtn=findViewById(R.id.moveToLocationServiceActivity)
        calculateBtn.setOnClickListener {
            calculateValues()
        }

        moveBtn.setOnClickListener {
            moveToSecondPage()
        }

        moveToAlertScreenBtn.setOnClickListener {
            moveToAlertScreen()
        }

        moveToCustomListScreenBtn.setOnClickListener {
            startActivity(Intent(MainActivity@this,CustomListActivity::class.java))
        }

        moveToStudentRoomActivityBtn.setOnClickListener {
            startActivity(Intent(MainActivity@this,StudentRoomActivity::class.java))
        }

//        moveToLocationServiceActivityBtn.setOnClickListener {
//            startActivity(Intent(MainActivity@this,LocationServiceActivity::class.java))
//        }

//        moveToLocationServiceActivityBtn.setOnClickListener {
//            startActivity(Intent(MainActivity@this,FirestoreWorkingActivity::class.java))
//        }

        moveToLocationServiceActivityBtn.setOnClickListener {
            startActivity(Intent(MainActivity@this,FbAuthWorkingActivity::class.java))
        }
    }

    private fun moveToAlertScreen() {
        startActivity(Intent(this,AlertDialogScreen::class.java))
    }

    private fun moveToSecondPage() {
        //Demo objDemo=new Demo();
        var objectIntent= Intent(this,SecondPage::class.java)
//        objectIntent.putExtra("name","Farrukh Ehsan")
//
//        objectIntent.putExtra("rollNo",12)

        var objectUser=User("Farrukh Ehsan",12)
        objectIntent.putExtra("user",objectUser)
        startActivity(objectIntent)
    }

    private fun calculateValues() {

        if(firstValueET.text.toString().isNotBlank()
            && secondValueET.text.toString().isNotBlank())
        {
            valueOne=firstValueET.text.toString().toInt()
            valueTwo=secondValueET.text.toString().toInt()

            varResult=valueOne+valueTwo
            resultTV.text=varResult.toString()

            firstValueET.text.clear()
            secondValueET.text.clear()

            firstValueET.requestFocus()
        }
        else if(firstValueET.text.toString().isBlank())
        {
            Toast.makeText(this,getString(R.string.warning_one),
            Toast.LENGTH_LONG).show()
            firstValueET.requestFocus()
        }
        else if(secondValueET.text.toString().isBlank())
        {
            Toast.makeText(this,getString(R.string.warning_two)
            ,Toast.LENGTH_LONG).show()
            secondValueET.requestFocus()
        }

    }


}