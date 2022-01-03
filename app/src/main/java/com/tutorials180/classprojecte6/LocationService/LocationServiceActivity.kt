package com.tutorials180.classprojecte6.LocationService

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.tutorials180.classprojecte6.databinding.ActivityLocationServiceBinding
import java.lang.Exception


/*
    Step 1: Add permissions to manifest file.
        i) ACCESS_FINE_LOCATION
        ii)ACCESS_COARSE_LOCATION
       iii)INTERNET

    Step 2: Add dependency into your build.gradle(app) file
        implementation 'com.google.android.gms:play-services-location:18.0.0'

    Step 3: Add views to activity xml file.
    Step 4: Create a binding object for xml file.

    Step 5: Create object of LocationManager class into our activity class
    Step 6: Inherit Location Listener class

    Step 7: Create a user defined method to get Current Location
    Step 8: Call the user defined method on the on click listener of getLocation Btn

    Step 9: call the "requestLocationUpdates" method with LocationManager object
    Step10: Add permission check with "requestLocationUpdates" method

    Step11: Check permission result with "onRequestPermissionsResult"
    Step12: Display lat and lng on the text view by writing code in "onLocationChanged" method
 */
class LocationServiceActivity : AppCompatActivity() , LocationListener
{
    private lateinit var mLocationServiceActivityBinding:ActivityLocationServiceBinding  //Declare
    private lateinit var mLocationManager: LocationManager //Declare

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mLocationServiceActivityBinding=ActivityLocationServiceBinding.inflate(layoutInflater) //Initialize

        setContentView(mLocationServiceActivityBinding.root)
        mLocationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager  //Initialize

        mLocationServiceActivityBinding.lsGetLocationBtn.setOnClickListener { getCurrentLocationOfDevice() }
    }

    //It will get the system current location
    private fun getCurrentLocationOfDevice()
    {
        try
        {

            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),2)
            }
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5f,this)
        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext,ex.message,Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==2) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Permission Not Granted", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onLocationChanged(location: Location) {
        try
        {
            mLocationServiceActivityBinding.lsLatLocationTv.text=location.latitude.toString()
            mLocationServiceActivityBinding.lsLngLocationTv.text=location.longitude.toString()
        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext,ex.message,Toast.LENGTH_LONG).show()
        }
    }


}