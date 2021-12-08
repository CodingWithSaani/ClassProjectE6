package com.tutorials180.classprojecte6.CustomArrayAdapterWork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.tutorials180.classprojecte6.R
import com.tutorials180.classprojecte6.databinding.ActivityCustomListBinding
import com.tutorials180.classprojecte6.databinding.ActivitySimpleListBinding
import java.lang.Exception

class CustomListActivity : AppCompatActivity() {

    private lateinit var cListBinding: ActivityCustomListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cListBinding= ActivityCustomListBinding.inflate(layoutInflater)
        setContentView(cListBinding.root)

        createPlayerList()
    }

    private fun createPlayerList()
    {
        try
        {
            var obj1=Player("Ali","Right Hand Batter",R.drawable.ic_main_iv)
            var obj2=Player("Ali","Right Hand Batter",R.drawable.ic_main_iv)

            var obj3=Player("Ali","Right Hand Batter",R.drawable.ic_main_iv)
            var obj4=Player("Ali","Right Hand Batter",R.drawable.ic_main_iv)

            var obj5=Player("Ali","Right Hand Batter",R.drawable.ic_main_iv)
            var playerList= listOf(obj1,obj2,obj3,obj4,obj5)

            cListBinding.customListView.adapter=CustomArrayAdapter(CustomListActivity@this,playerList)
        }
        catch (ex:Exception)
        {
            Toast.makeText(CustomListActivity@this,ex.message,Toast.LENGTH_LONG).show()
        }
    }
}