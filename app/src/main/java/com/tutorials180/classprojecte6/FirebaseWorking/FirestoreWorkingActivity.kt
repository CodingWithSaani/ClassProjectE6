package com.tutorials180.classprojecte6.FirebaseWorking

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.WriteBatch
import com.google.firebase.firestore.ktx.toObject
import com.tutorials180.classprojecte6.FirebaseWorking.FirebaseModels.CarModelForFB
import com.tutorials180.classprojecte6.R
import com.tutorials180.classprojecte6.databinding.ActivityFirestoreWorkingBinding
import java.lang.Exception

class FirestoreWorkingActivity : AppCompatActivity() {

    private lateinit var mFSWBinder:ActivityFirestoreWorkingBinding
    private lateinit var mFirebaseFirestore:FirebaseFirestore

    private lateinit var mProgressDialog:ProgressDialog
    private lateinit var mWriteBatch: WriteBatch

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mFSWBinder= ActivityFirestoreWorkingBinding.inflate(layoutInflater)
        setContentView(mFSWBinder.root)

        mProgressDialog= ProgressDialog(this)
        mProgressDialog.setMessage("Please wait...")


        mFirebaseFirestore= FirebaseFirestore.getInstance()
        mWriteBatch= mFirebaseFirestore.batch()

        mFSWBinder.fsWorkingAddSingleDocBtn.setOnClickListener { addSingleDocumentIntoFireStore() }

        mFSWBinder.fsWorkingAddSingleDocModelBtn.setOnClickListener {
            addSingleDataModelDocumentIntoFireStore()
        }

        mFSWBinder.fsWorkingGetSingleDocModelBtn.setOnClickListener {
            getSingleDocument()
        }

        mFSWBinder.fsWorkingAddSingleDocModelWithoutIdBtn.setOnClickListener {
            addSingleDataModelDocumentIntoFireStoreWithoutID()
        }

        mFSWBinder.fsWorkingGetAllDocs.setOnClickListener { getAllDocuments() }
        mFSWBinder.fsWorkingPerformWriteBatchBtn.setOnClickListener { performWriteBatch() }

        mFSWBinder.fsWorkingDeleteDocBtn.setOnClickListener { deleteSingleDocument() }
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
            val car=CarModelForFB("Honda",2021,"Manual")
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

    private fun addSingleDataModelDocumentIntoFireStoreWithoutID()
    {
        try
        {
            mProgressDialog.show()
            val car=CarModelForFB("Honda",2021,"Manual")
            mFirebaseFirestore.collection("Cars")
                .add(car)
                .addOnSuccessListener {doc->
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

    private fun getAllDocuments()
    {
        try
        {
            mFirebaseFirestore.collection("Cars")
                .get()
                .addOnSuccessListener { docs ->
                    if(docs.isEmpty)
                    {
                        Toast.makeText(applicationContext, "Docs are empty", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        for(currentCar in docs)
                        {
                            val returnedCar=currentCar.toObject<CarModelForFB>()
                            Toast.makeText(applicationContext,
                                "Doc id:${currentCar.id.toString()}\n" +
                                        "Car Company:${returnedCar?.carCompanyName}\n" +
                                        "Car Model:${returnedCar?.carModel}\n" +
                                        "Car Type:${returnedCar?.carType}",Toast.LENGTH_LONG).show()
                        }
                    }

                }
                .addOnFailureListener { fsEx->

                }
        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext, "Exception:${ex.message}", Toast.LENGTH_SHORT).show();
        }
    }

    private fun performWriteBatch()
    {
        try
        {
            mProgressDialog.show()

            var car1= CarModelForFB("Car1",2000,"M/A")
            var car2= CarModelForFB("Car2",1000,"M")

            var car3= CarModelForFB("Car3",500,"M")
            var car4= CarModelForFB("Car4",100,"M")

            mWriteBatch.set(mFirebaseFirestore.collection("Cars").document(),car1)
            mWriteBatch.set(mFirebaseFirestore.collection("Cars").document(),car2)

            mWriteBatch.set(mFirebaseFirestore.collection("Cars").document(),car3)
            mWriteBatch.set(mFirebaseFirestore.collection("Cars").document(),car4)

            mWriteBatch.commit()
                .addOnSuccessListener { _:Void?->
                    mProgressDialog.dismiss()
                    Toast.makeText(applicationContext, "All Documents added", Toast.LENGTH_SHORT).show();
                }
                .addOnFailureListener { batchException->
                    mProgressDialog.dismiss()
                    Toast.makeText(applicationContext, "Batch Exception:${batchException.message}", Toast.LENGTH_SHORT).show();
                }
        }
        catch (ex:Exception)
        {
            mProgressDialog.dismiss()
            Toast.makeText(applicationContext, "Exception:${ex.message}", Toast.LENGTH_SHORT).show();
        }
    }

    private fun deleteSingleDocument()
    {
        try
        {
            mProgressDialog.show()
            mFirebaseFirestore.collection("Cars")
                .document("car1")
                .delete()
                .addOnSuccessListener {_:Void?->
                    mProgressDialog.dismiss()
                    Toast.makeText(applicationContext, "Document is deleted", Toast.LENGTH_SHORT).show();
                }
                .addOnFailureListener {ex->
                    mProgressDialog.dismiss()
                    Toast.makeText(applicationContext, "Exception:${ex.message}", Toast.LENGTH_SHORT).show();
                }
        }
        catch (ex:Exception)
        {
            mProgressDialog.dismiss()
            Toast.makeText(applicationContext, "Exception:${ex.message}", Toast.LENGTH_SHORT).show();
        }
    }



















}