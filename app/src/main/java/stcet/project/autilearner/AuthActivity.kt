package stcet.project.autilearner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val register = intent.getStringExtra("Register?")

        if (savedInstanceState == null){
            if (register == "1")
                useRegisterFragment()
            else
                useLoginFragment()
        }
    }

    fun useRegisterFragment(){
        val registerFragment = RegisterFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_left)
        transaction.replace(R.id.auth_fragment,registerFragment).commitAllowingStateLoss()
    }

    fun useLoginFragment(){
        val loginFragment = LoginFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_from_right)
        transaction.replace(R.id.auth_fragment,loginFragment).commitAllowingStateLoss()
    }
}