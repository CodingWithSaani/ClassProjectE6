package com.tutorials180.classprojecte6.FirebaseWorking

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.tutorials180.classprojecte6.FirebaseWorking.FirebaseModels.CarModelForFB
import com.tutorials180.classprojecte6.R
import com.tutorials180.classprojecte6.databinding.ActivityFirestoreWorkingBinding
import java.lang.Exception

class FirestoreWorkingActivity : AppCompatActivity() {

    private lateinit var mFSWBinder:ActivityFirestoreWorkingBinding
    private lateinit var mFirebaseFirestore:FirebaseFirestore

    private lateinit var mProgressDialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mFSWBinder= ActivityFirestoreWorkingBinding.inflate(layoutInflater)
        setContentView(mFSWBinder.root)

        mProgressDialog= ProgressDialog(this)
        mProgressDialog.setMessage("Please wait...")


        mFirebaseFirestore= FirebaseFirestore.getInstance()
        mFSWBinder.fsWorkingAddSingleDocBtn.setOnClickListener { addSingleDocumentIntoFireStore() }

        mFSWBinder.fsWorkingAddSingleDocModelBtn.setOnClickListener {
            addSingleDataModelDocumentIntoFireStore()
        }

        mFSWBinder.fsWorkingGetSingleDocModelBtn.setOnClickListener {
            getSingleDocument()
        }
    }

    private fun addSingleDocumentIntoFireStore()
    {
        try
        {
            var currentSamosa=HashMap<String,Any>()
            currentSamosa["samosaName"] = "Simple Samosa"

            currentSamosa["samosaType"]="Chicken"
            mFirebaseFirestore.collection("Samosa")
                .add(currentSamosa)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext,"document Added",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(applicationContext,"document not Added",Toast.LENGTH_SHORT).show()
                }
        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext,ex.message,Toast.LENGTH_SHORT).show()
        }
    }

    private fun addSingleDataModelDocumentIntoFireStore()
    {
        try
        {
            mProgressDialog.show()
            val car=CarModelForFB("Toyota",2020,"Auto")
            mFirebaseFirestore.collection("Cars")
                .document("car1")
                .set(car)
                .addOnSuccessListener {_:Void?->
                    mProgressDialog.dismiss()
                    Toast.makeText(applicationContext,"Record Added",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {fbException->
                    mProgressDialog.dismiss()
                    Toast.makeText(applicationContext,"FB Exception:${fbException.message}",Toast.LENGTH_SHORT).show()
                }
        }
        catch (ex:Exception)
        {
            mProgressDialog.dismiss()
            Toast.makeText(applicationContext,ex.message,Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSingleDocument()
    {
        try
        {
            mFirebaseFirestore.collection("Cars")
                .document("car1")
                .get()
                .addOnSuccessListener {document->
                    if(document.exists())
                    {
                        val returnedCar=document.toObject<CarModelForFB>()
                        Toast.makeText(applicationContext,
                        "Car Company:${returnedCar?.carCompanyName}\n" +
                                "Car Model:${returnedCar?.carModel}\n" +
                                "Car Type:${returnedCar?.carType}",Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"Document is empty",Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {

                }
        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext,ex.message,Toast.LENGTH_SHORT).show()
        }
    }







}