package com.tutorials180.classprojecte6.SimpleRoomImplementation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDAO {

    @Insert
    fun addStudent(student:Student):Long

    @Query("select * from Student")
    fun getAllStudents():List<Student>


}