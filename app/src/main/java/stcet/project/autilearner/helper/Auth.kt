package stcet.project.autilearner.helper

import com.google.firebase.auth.FirebaseAuth

class Auth {

    private lateinit var clientinstance : FirebaseAuth

    fun checkExistingUser() : Boolean{
        clientinstance = FirebaseAuth.getInstance()
        return ( clientinstance.currentUser != null )
    }
}