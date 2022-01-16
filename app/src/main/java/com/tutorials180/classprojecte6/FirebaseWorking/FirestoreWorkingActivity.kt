package com.tutorials180.classprojecte6.FirebaseWorking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.tutorials180.classprojecte6.R
import com.tutorials180.classprojecte6.databinding.ActivityFirestoreWorkingBinding
import java.lang.Exception

class FirestoreWorkingActivity : AppCompatActivity() {

    private lateinit var mFSWBinder:ActivityFirestoreWorkingBinding
    private lateinit var mFirebaseFirestore:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mFSWBinder= ActivityFirestoreWorkingBinding.inflate(layoutInflater)
        setContentView(mFSWBinder.root)

        mFirebaseFirestore= FirebaseFirestore.getInstance()
        mFSWBinder.fsWorkingAddSingleDocBtn.setOnClickListener { addSingleDocumentIntoFireStore() }
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












}