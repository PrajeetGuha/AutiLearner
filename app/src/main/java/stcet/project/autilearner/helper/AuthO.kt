package stcet.project.autilearner.helper

import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import stcet.project.autilearner.R

class AuthO {

    fun getUser() : FirebaseUser?{
        return FirebaseAuth.getInstance().currentUser
    }

    fun registerUser(email : String, password : String) : Boolean{
        var result = false
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful)
                result = true
        }
        return result
    }

    fun loginUser(email: String, password: String) : Boolean{
        var result = false
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                result = true
            }
        }
        return result
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