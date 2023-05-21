package stcet.project.autilearner.helper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import stcet.project.autilearner.R
import stcet.project.autilearner.data.UserDataManager
import stcet.project.autilearner.home.MainActivity

class AuthO {

    private val debugTag = "AUTH"
    private val userDataCollection = FirebaseFirestore.getInstance().collection("user_data")

    fun getUser() : FirebaseUser?{
        userDataCollection.whereEqualTo("uid",FirebaseAuth.getInstance().currentUser?.uid).get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                UserDataManager.getInstance().setData(document.data)
            }
        }
        return FirebaseAuth.getInstance().currentUser
    }

    fun registerUser(email : String, password : String, view : View){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                val dataMap = mutableMapOf(
                    "uid" to it.result?.user?.uid,
                    "learn_emotion" to mutableListOf<Boolean>(false,false,false,false),
                    "learn_n_play" to mutableListOf<Boolean>(false,false,false,false)
                )
                userDataCollection.document().set(dataMap).addOnSuccessListener {
                    Log.d(debugTag,"User Data Initially Persisted")
                    Log.d(debugTag,"User successfully registered")
                    val main = Intent(view.context, MainActivity::class.java)
                    view.context.startActivity(main)
                }.addOnFailureListener {
                    Log.d(debugTag,"User Data Initially Cannot Be Persisted")
                    Log.d(debugTag,"User registration failed")
                    Toast.makeText(view.context,"User Already Registered", Toast.LENGTH_SHORT).show()
                    FirebaseAuth.getInstance().currentUser?.delete()?.addOnCompleteListener {
                        if(it.isSuccessful){
                            Log.d(debugTag,"Registered User Successfully deleted")
                        }
                        else{
                            Log.d(debugTag,"Database went to unreliable state")
                        }
                    }
                }
            }
            else{
                Log.d(debugTag,"User registration failed")
                Toast.makeText(view.context,"User Already Registered", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Log.d(debugTag,"Unable to perform registration due to firestore server issue")
            Toast.makeText(view.context,"Unable to connect with the server", Toast.LENGTH_SHORT).show()
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
        }.addOnFailureListener {
            Log.d(debugTag,"Unable to perform login due to firestore server issue")
            Toast.makeText(view.context,"Unable to connect with the server", Toast.LENGTH_SHORT).show()
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