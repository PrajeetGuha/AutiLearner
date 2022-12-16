package stcet.project.autilearner.helper

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import stcet.project.autilearner.MainActivity
import stcet.project.autilearner.RegisterActivity

class Identity {

    fun checkExistingUser() : Boolean{
        return FirebaseAuth.getInstance().currentUser != null
    }

    fun registerUser(activity: RegisterActivity, email : String, password : String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                Log.d("REGISTER","User successfully registered")
                Toast.makeText(activity,"Successfully Registered",Toast.LENGTH_SHORT).show()
                val main = Intent(activity,MainActivity::class.java)
                activity.startActivity(main)
            }
            else{
                Log.d("REGISTER","User registration failed")
                Toast.makeText(activity,"Registration Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getUser() : FirebaseUser?{
        return FirebaseAuth.getInstance().currentUser
    }
}