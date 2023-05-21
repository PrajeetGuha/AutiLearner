package stcet.project.autilearner.learn_emotion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import stcet.project.autilearner.R

class LearnEmotionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_emotion)
//        val user = AuthO().getUser()
//        if(user == null){
//            val splash = Intent(this, SplashActivity::class.java)
//            startActivity(splash)
//        }
        clickListener()
    }

    private fun clickListener() {
        var lesson1 = findViewById<Button>(R.id.emotion_lesson1)
        lesson1.setOnClickListener {
            openLessonActivity("1")
        }

        var lesson2 = findViewById<Button>(R.id.emotion_lesson2)
        lesson2.setOnClickListener {
            openLessonActivity("2")
        }

        var lesson3 = findViewById<Button>(R.id.emotion_lesson3)
        lesson3.setOnClickListener {
            openLessonActivity("3")
        }

        var lesson4 = findViewById<Button>(R.id.emotion_lesson4)
        lesson4.setOnClickListener {
            openLessonActivity("4")
        }
    }

    private fun openLessonActivity(lessonNumber : String) {
        val intent = Intent(this, EmotionLessonActivity::class.java)
        intent.putExtra("lesson_number",lessonNumber)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
//        val user = AuthO().getUser()
//        if(user == null){
//            val splash = Intent(this, SplashActivity::class.java)
//            startActivity(splash)
//        }
    }
}