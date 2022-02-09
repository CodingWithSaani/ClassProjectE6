package com.tutorials180.classprojecte6.FirebaseWorking

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tutorials180.classprojecte6.R
import com.tutorials180.classprojecte6.databinding.ActivityFbAuthWorkingBinding
import java.lang.Exception

class FbAuthWorkingActivity : AppCompatActivity()
{
    private lateinit var mFBAuthWorkingBinding:ActivityFbAuthWorkingBinding
    private lateinit var mFirebaseAuth : FirebaseAuth

    private lateinit var mProgressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mFBAuthWorkingBinding = ActivityFbAuthWorkingBinding.inflate(layoutInflater)

        setContentView(mFBAuthWorkingBinding.root)
        mProgressDialog = ProgressDialog(this)

        mProgressDialog.setMessage("Please wait...")
        mFirebaseAuth  = Firebase.auth

        mFBAuthWorkingBinding.fbAuthSignupBtn.setOnClickListener { signUpUser() }
    }

    private fun signUpUser()
    {
        try
        {
            if(mFBAuthWorkingBinding.fbAuthEmailEt.text.isNotBlank()
                && mFBAuthWorkingBinding.fbAuthPasswordEt.text.isNotBlank())
            {
                mProgressDialog.show()
                mFirebaseAuth.createUserWithEmailAndPassword(mFBAuthWorkingBinding.fbAuthEmailEt.text.toString(),
                mFBAuthWorkingBinding.fbAuthPasswordEt.text.toString())
                    .addOnCompleteListener { it ->
                        mProgressDialog.dismiss()
                        if(it.isSuccessful)
                        {
                            Toast.makeText(applicationContext, "User is created", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(applicationContext, "Failed to craeted user", Toast.LENGTH_SHORT).show();
                        }
                    }
                    .addOnFailureListener { it ->
                        mProgressDialog.dismiss()
                        Toast.makeText(applicationContext, "Exception while creating user:${it.message}", Toast.LENGTH_SHORT).show();
                    }
            }
            else
            {
                Toast.makeText(applicationContext, "Some Feilds are empty", Toast.LENGTH_SHORT).show();
            }
        }
        catch (ex:Exception)
        {
            mProgressDialog.dismiss()
            Toast.makeText(applicationContext, "${ex.message}", Toast.LENGTH_SHORT).show();
        }
    }

    private fun signOutUser()
    {
        try
        {
            val currentLoggedInUser=FirebaseAuth.getInstance().currentUser
            if(currentLoggedInUser==null)
            {
                Toast.makeText(applicationContext, "No User is logged in", Toast.LENGTH_SHORT).show();
            }
            else
            {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(applicationContext, "Logged Out", Toast.LENGTH_SHORT).show();
            }
        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext, "${ex.message}", Toast.LENGTH_SHORT).show();
        }
    }
















}