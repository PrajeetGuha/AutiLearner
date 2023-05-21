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
            showIntent("learnAZ")
        }

        val learnShapes = findViewById<CardView>(R.id.learn_shapes)
        learnShapes.setOnClickListener{
            showIntent("shapes")
        }

        val learnAnimals = findViewById<CardView>(R.id.learn_animals)
        learnAnimals.setOnClickListener{
            showIntent("animals")
        }
    }

    private fun showIntent(categoryNumber : String){
        val intent = Intent(this,LearnNPlayLessonActivity::class.java)
        intent.putExtra("category",categoryNumber)
        startActivity(intent)
    }
}