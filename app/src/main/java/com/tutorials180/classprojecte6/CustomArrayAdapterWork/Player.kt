package com.tutorials180.classprojecte6.CustomArrayAdapterWork

class Player(name:String,battingStyle:String,image:Int) {

    private var playerName=name
    private var playerBattingStyle=battingStyle

    private var playerImage=image

    fun getPlayerName():String{
        return playerName
    }

    fun getPlayerBattingStyle():String
    {
        return playerBattingStyle
    }

    fun getPlayerImage():Int
    {
        return playerImage
    }
}