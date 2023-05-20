package stcet.project.autilearner.helper

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import stcet.project.autilearner.R
import stcet.project.autilearner.home.MainActivity

class AuthO {

    fun getUser() : FirebaseUser?{
        return FirebaseAuth.getInstance().currentUser
    }

    suspend fun registerUser(email : String, password : String, view : View){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                Log.d("REGISTER","User successfully registered")
                val main = Intent(view.context, MainActivity::class.java)
                view.context.startActivity(main)
            }
            else{
                Log.d("REGISTER","User registration failed")
                Toast.makeText(view.context,"User Already Registered", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun loginUser(email: String, password: String, view : View){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Log.d("LOGIN", "User successfully logged in")
                val main = Intent(view.context, MainActivity::class.java)
                view.context.startActivity(main)
            }
            else{
                Log.d("LOGIN","User credential is wrong or not registered")
                Toast.makeText(view.context,"Invalid Credential or Check Internet",Toast.LENGTH_SHORT).show()
            }
        }
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