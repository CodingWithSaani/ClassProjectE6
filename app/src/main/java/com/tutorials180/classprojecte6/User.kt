package com.tutorials180.classprojecte6

import java.io.Serializable

class User (var name:String,var rollNo:Int):Serializable {

    fun returnName():String
    {
        return name
    }

    fun returnRollNo():Int
    {
        return rollNo
    }
}