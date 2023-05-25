package stcet.project.autilearner.learn_and_play

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import stcet.project.autilearner.R
import stcet.project.autilearner.home.MainActivity

class LearnNPlayResultActivity : AppCompatActivity() {
    private lateinit var actualResult : TextView
    private var doubleBackPressed = false

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
        clickListener()
    }

    private fun clickListener() {
        val home = findViewById<Button>(R.id.back_to_home)
        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (doubleBackPressed){
            finishAffinity()
            finish()
        }
        else{
            doubleBackPressed = true
            Toast.makeText(this,getString(R.string.back_button_twice_to_exit), Toast.LENGTH_SHORT).show()
        }
    }
}