package stcet.project.autilearner.learn_and_play

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import stcet.project.autilearner.R

class LearnAndPlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_and_play)
        clickListener()
    }

    private fun clickListener() {
        val learnAZ = findViewById<CardView>(R.id.learn_AZ)
        learnAZ.setOnClickListener{
            openLearnLetters()
        }

        val learnShapes = findViewById<CardView>(R.id.learn_shapes)
        learnShapes.setOnClickListener{
            openLearnShapes()
        }

        val learnAnimals = findViewById<CardView>(R.id.learn_animals)
        learnAnimals.setOnClickListener{
            openLearnAnimals()
        }
    }

    private fun openLearnLetters(){
        val intent = Intent(this,LearnLettersLessonActivity::class.java)
            startActivity(intent)
    }
    private fun openLearnAnimals() {
            val intent = Intent(this, LearnAnimalsLessonActivity::class.java)
            startActivity(intent)
    }
    private fun openLearnShapes() {
        val intent = Intent(this, LearnShapesLessonActivity::class.java)
        startActivity(intent)
    }
}