package stcet.project.autilearner.learn_emotion

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import stcet.project.autilearner.R
import stcet.project.autilearner.authentication.SplashActivity
import stcet.project.autilearner.helper.AuthO
import stcet.project.autilearner.home.MainActivity

class ResultActivity : AppCompatActivity() {
    private lateinit var actualResult : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        clickListener()

        val score = intent.getStringExtra("total_correct")
        val totalMarks = intent.getStringExtra("total_questions")
        actualResult = findViewById(R.id.score)
        actualResult.text = buildString {
            append(score)
            append(" / ")
            append(totalMarks)
        }
    }

    private fun clickListener() {
        val home = findViewById<Button>(R.id.back_to_home)
        home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    override fun onStart() {
        super.onStart()
//        val user = AuthO().getUser()
//        if(user == null){
//            val splash = Intent(this, SplashActivity::class.java)
//            startActivity(splash)
//        }
    }

    override fun onBackPressed() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}