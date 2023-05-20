package stcet.project.autilearner.learn_emotion

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import stcet.project.autilearner.R

class ResultActivity : AppCompatActivity() {
    private lateinit var actualResult : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val score = intent.getStringExtra("total_correct")
        val totalMarks = intent.getStringExtra("total_questions")
        actualResult = findViewById(R.id.score)
        actualResult.text = buildString {
            append(score)
            append(" / ")
            append(totalMarks)
        }
    }
}