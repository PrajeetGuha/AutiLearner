package stcet.project.autilearner.authentication

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import stcet.project.autilearner.R
import stcet.project.autilearner.helper.AuthO

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private val animationDuration : Long = 3000
    private var doubleBackPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startAnimations()

        findViewById<Button>(R.id.registerbutton).setOnClickListener(this)
        findViewById<Button>(R.id.loginbutton).setOnClickListener(this)
    }

    override fun onStart(){
        super.onStart()
        val auth = AuthO()
        val currentUser = auth.getUser()
        if (auth.getUser() != null){
            val main = Intent(this, MainActivity::class.java)
            startActivity(main)
        }
    }

    override fun onResume() {
        super.onResume()
        doubleBackPressed = false
    }

    override fun onClick(view : View?){
        when(view?.id){

            R.id.registerbutton -> {
                val auth = Intent(this, AuthActivity::class.java)
                auth.putExtra("Register?",true)
                startActivity(auth)
            }
            R.id.loginbutton -> {
                val auth = Intent(this, AuthActivity::class.java)
                auth.putExtra("Register?",false)
                startActivity(auth)
            }
        }
    }

    private fun startAnimations(){

        val animationView = findViewById<LottieAnimationView>(R.id.loading)
        val taglineView = findViewById<TextView>(R.id.tagline)
        val loginbutton = findViewById<Button>(R.id.loginbutton)
        val registerbutton = findViewById<Button>(R.id.registerbutton)

        val viewitems = arrayOf(animationView,taglineView,loginbutton,registerbutton)

        for (v in viewitems){
            v.alpha = 0f
            ObjectAnimator.ofFloat(v,"alpha",1f).apply{
                duration = animationDuration
                start()
            }
        }
    }

    override fun onBackPressed() {
        if (doubleBackPressed)
            return super.onBackPressed()
        else{
            doubleBackPressed = true
            Toast.makeText(this,getString(R.string.back_button_twice_to_exit),Toast.LENGTH_SHORT).show()
        }
    }

}