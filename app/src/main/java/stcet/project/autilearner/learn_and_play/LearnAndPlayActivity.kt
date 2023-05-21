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
            showIntent("1")
        }

        val learnShapes = findViewById<CardView>(R.id.learn_shapes)
        learnAZ.setOnClickListener{
            showIntent("2")
        }

        val learnAnimals = findViewById<CardView>(R.id.learn_animals)
        learnAZ.setOnClickListener{
            showIntent("3")
        }
    }

    private fun showIntent(categoryNumber : String){
        val intent = Intent(this,LessonActivity::class.java)
        intent.putExtra("category",categoryNumber)
        startActivity(intent)
    }
}