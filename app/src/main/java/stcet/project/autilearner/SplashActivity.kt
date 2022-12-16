package stcet.project.autilearner

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import stcet.project.autilearner.helper.Identity

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private val animationDuration : Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startAnimations()

        findViewById<Button>(R.id.registerbutton).setOnClickListener(this)
    }

    override fun onStart(){
        super.onStart()
        val auth = Identity()
        if (auth.checkExistingUser()){
            val main = Intent(this,MainActivity::class.java)
            startActivity(main)
        }
    }

    override fun onClick(view : View?){
        when(view?.id){

            R.id.registerbutton -> {
                val register = Intent(this,RegisterActivity::class.java)
                startActivity(register)
            }
        }
    }

    private fun startAnimations(){

        val animationView = findViewById<LottieAnimationView>(R.id.loading)
        val taglineView = findViewById<TextView>(R.id.tagline)
        val loginbutton = findViewById<Button>(R.id.loginbutton)
        val registerbutton = findViewById<Button>(R.id.registerbutton)

        val viewitems = arrayOf(taglineView,loginbutton,registerbutton)

        animationView.setMinAndMaxFrame(0,30)
        ObjectAnimator.ofFloat(animationView,"translationY",-200f).apply {
            duration = animationDuration
            start()
        }

        for (v in viewitems){
            v.alpha = 0f
            ObjectAnimator.ofFloat(v,"alpha",1f).apply{
                duration = animationDuration
                start()
            }
        }
    }

}