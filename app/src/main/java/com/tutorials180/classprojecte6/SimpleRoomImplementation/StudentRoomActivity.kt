package com.tutorials180.classprojecte6.SimpleRoomImplementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.tutorials180.classprojecte6.R
import com.tutorials180.classprojecte6.databinding.ActivityStudentRoomBinding

class StudentRoomActivity : AppCompatActivity() {

    private lateinit var objActivityStudentRoom:ActivityStudentRoomBinding
    private lateinit var objStudentDb:StudentDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        objActivityStudentRoom= ActivityStudentRoomBinding.inflate(layoutInflater)
        setContentView(objActivityStudentRoom.root)

        createStudentDb()
        objActivityStudentRoom.addStudentBtn.setOnClickListener {
            addStudentToDb()
        }

        objActivityStudentRoom.getStudentBtn.setOnClickListener {
            getAllStudents()
        }
    }

    private fun createStudentDb()
    {
        try
        {
            objStudentDb= Room.databaseBuilder(applicationContext,
            StudentDatabase::class.java,"Student Database")
                .allowMainThreadQueries()
                .build()
        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext,ex.toString(),Toast.LENGTH_LONG).show()
        }
    }

    private fun getAllStudents()
    {
        try
        {
            val listOfStudents=objStudentDb.getDAOObject().getAllStudents()
            objActivityStudentRoom.studentRv.layoutManager=LinearLayoutManager(this)

            if(listOfStudents.isNotEmpty()) {
                objActivityStudentRoom.studentRv.adapter = RVCustomAdapter(listOfStudents)
            }
            else
            {
                Toast.makeText(applicationContext,"No student record found",Toast.LENGTH_LONG).show()
            }

        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext,ex.toString(),Toast.LENGTH_LONG).show()
        }
    }

    private fun addStudentToDb()
    {
        try
        {
            if(objActivityStudentRoom.studentIdTv.text.toString().isNotBlank()
                && objActivityStudentRoom.studentNameTv.text.toString().isNotBlank()
                && objActivityStudentRoom.studentEmailTv.text.toString().isNotBlank())
            {
                val currentStudent=Student(objActivityStudentRoom.studentIdTv.text.toString().toInt(),
                objActivityStudentRoom.studentNameTv.text.toString(),
                objActivityStudentRoom.studentEmailTv.text.toString())

                val queryCheck=objStudentDb.getDAOObject().addStudent(currentStudent)
                if(queryCheck!=0L)
                {
                    Toast.makeText(applicationContext,"Student Added",Toast.LENGTH_LONG)
                        .show()
                }
                else
                {
                    Toast.makeText(applicationContext,"Fails to add student",Toast.LENGTH_LONG)
                        .show()
                }
            }
            else
            {
                Toast.makeText(applicationContext,"Some fields are left empty",Toast.LENGTH_LONG)
                    .show()
            }
        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext,ex.toString(),Toast.LENGTH_LONG).show()
        }
    }









}