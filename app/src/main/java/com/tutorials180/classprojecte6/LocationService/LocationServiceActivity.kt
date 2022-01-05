package com.tutorials180.classprojecte6.LocationService

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.tutorials180.classprojecte6.databinding.ActivityLocationServiceBinding
import java.lang.Exception
import java.util.*

class LocationServiceActivity : AppCompatActivity() , LocationListener
{
    private lateinit var mLocationServiceActivityBinding:ActivityLocationServiceBinding  //Declare
    private lateinit var mLocationManager: LocationManager //Declare

    private lateinit var mGeoCoder:Geocoder  //Declare
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mLocationServiceActivityBinding=ActivityLocationServiceBinding.inflate(layoutInflater) //Initialize

        setContentView(mLocationServiceActivityBinding.root)
        mLocationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager  //Initialize

        mGeoCoder= Geocoder(LocationServiceActivity@this, Locale.getDefault())  //Initialize
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
            mLocationServiceActivityBinding.lsLatLocationTv.text="Latitude:${location.latitude}"
            mLocationServiceActivityBinding.lsLngLocationTv.text="Longitude:${location.longitude}"

            getActualAddress(location.latitude,location.longitude)
        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext,ex.message,Toast.LENGTH_LONG).show()
        }
    }

    //
    private fun getActualAddress(lat:Double,lng:Double)
    {
        try
        {
            val currentAddress=mGeoCoder.getFromLocation(lat,lng,1) //it will return us Address in a list
            val streetAdd=currentAddress[0].getAddressLine(0)

            val cityName=currentAddress[0].locality
            val provinceName=currentAddress[0].adminArea

            val zipCode=currentAddress[0].postalCode
            val countryName=currentAddress[0].countryName

            mLocationServiceActivityBinding.lsActualAddressTv.text=streetAdd
            mLocationServiceActivityBinding.lsCityTv.text=cityName

            mLocationServiceActivityBinding.lsProvinceTv.text=provinceName
            mLocationServiceActivityBinding.lsZipCodeTv.text=zipCode.toString()

            mLocationServiceActivityBinding.lsCountryTv.text=countryName
        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext,ex.message,Toast.LENGTH_LONG).show()
        }
    }


}