package stcet.project.autilearner.helper

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import stcet.project.autilearner.MainActivity
import stcet.project.autilearner.R

class AuthO {

    fun checkExistingUser() : Boolean{
        return FirebaseAuth.getInstance().currentUser != null
    }

    fun registerUser(activity : Context, email : String, password : String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                Log.d("REGISTER","User successfully registered")
                Toast.makeText(activity,"Successfully Registered",Toast.LENGTH_SHORT).show()
                val main = Intent(activity,MainActivity::class.java)
                activity.startActivity(main)
            }
            else{
                Log.d("REGISTER","User registration failed")
                Toast.makeText(activity,"User Already Registered",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun loginUser(activity: Context, email: String, password: String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Log.d("LOGIN","User successfully logged in")
                Toast.makeText(activity,"Successfully Logged In",Toast.LENGTH_SHORT).show()
                val main = Intent(activity,MainActivity::class.java)
                activity.startActivity(main)
            }
            else{
                Log.d("LOGIN","User credential is wrong or not registered")
                Toast.makeText(activity,"User is not registered or wrong credentials",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getUserID() : String?{
        return FirebaseAuth.getInstance().currentUser?.uid
    }

    fun registerUsingGoogle(context: Context){
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(context.getString(R.string.web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build()
    }
}