package stcet.project.autilearner.learn_and_play

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import stcet.project.autilearner.R
import stcet.project.autilearner.home.MainActivity

class LearnNPlayResultActivity : AppCompatActivity() {
    private lateinit var actualResult : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val score = intent.getStringExtra("total_correct")
        val totalMarks = intent.getStringExtra("total_questions")
        Log.d("CHECK",totalMarks.toString())
        actualResult = findViewById(R.id.score)
        actualResult.text = buildString {
            append(score)
            append(" / ")
            append(totalMarks)
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
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}