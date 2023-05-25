package stcet.project.autilearner.home

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

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private val animationDuration : Long = 3000
    private var doubleBackPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startAnimations()
        findViewById<Button>(R.id.startbutton).setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        doubleBackPressed = false
    }

    override fun onClick(view : View?){
        when(view?.id){
            R.id.startbutton -> {
                val start = Intent(this, MainActivity::class.java)
                startActivity(start)
            }
        }
    }

    private fun startAnimations(){
        val animationView = findViewById<LottieAnimationView>(R.id.loading)
        val taglineView = findViewById<TextView>(R.id.tagline)
        val startbutton = findViewById<Button>(R.id.startbutton)
        val viewitems = arrayOf(animationView,taglineView,startbutton)
        for (v in viewitems){
            v.alpha = 0f
            ObjectAnimator.ofFloat(v,"alpha",1f).apply{
                duration = animationDuration
                start()
            }
        }
    }

    override fun onBackPressed() {
        if (doubleBackPressed){
            finishAffinity()
            finish()
        }
        else{
            doubleBackPressed = true
            Toast.makeText(this,getString(R.string.back_button_twice_to_exit),Toast.LENGTH_SHORT).show()
        }
    }

}