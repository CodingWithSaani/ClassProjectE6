package com.tutorials180.classprojecte6.SimpleRoomImplementation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student (@PrimaryKey val id:Int, @ColumnInfo(name = "studentName") val name:String, val email:String)
