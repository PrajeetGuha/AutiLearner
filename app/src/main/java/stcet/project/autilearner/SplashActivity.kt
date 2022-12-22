package stcet.project.autilearner

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import stcet.project.autilearner.helper.AuthO

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private val animationDuration : Long = 3000

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
        if (auth.checkExistingUser()){
            val main = Intent(this,MainActivity::class.java)
            startActivity(main)
        }
    }

    override fun onClick(view : View?){
        when(view?.id){

            R.id.registerbutton -> {
                val auth = Intent(this,AuthActivity::class.java)
                auth.putExtra("Register?","1")
                startActivity(auth)
            }
            R.id.loginbutton -> {
                val auth = Intent(this,AuthActivity::class.java)
                auth.putExtra("Register?","0")
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

}