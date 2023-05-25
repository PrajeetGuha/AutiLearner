package stcet.project.autilearner.learn_emotion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import stcet.project.autilearner.R
import stcet.project.autilearner.home.MainActivity

class LearnEmotionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_emotion)
        clickListener()
    }

    private fun clickListener() {
        val lesson1 = findViewById<Button>(R.id.emotion_lesson1)
        lesson1.setOnClickListener {
            openLessonActivity("1")
        }
        val lesson2 = findViewById<Button>(R.id.emotion_lesson2)
        lesson2.setOnClickListener {
            openLessonActivity("2")
        }
        val lesson3 = findViewById<Button>(R.id.emotion_lesson3)
        lesson3.setOnClickListener {
            openLessonActivity("3")
        }
        val lesson4 = findViewById<Button>(R.id.emotion_lesson4)
        lesson4.setOnClickListener {
            openLessonActivity("4")
        }
        val social_story = findViewById<Button>(R.id.emotion_social_story)
        social_story.setOnClickListener {
            openSocialStory()
        }
    }

    private fun openLessonActivity(lessonNumber : String) {
        val intent = Intent(this, EmotionLessonActivity::class.java)
        intent.putExtra("lesson_number",lessonNumber)
        startActivity(intent)
    }

    private fun openSocialStory() {
        startActivity(Intent(this, SocialStoryActivity::class.java))
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}