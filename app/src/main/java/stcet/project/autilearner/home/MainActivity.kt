package stcet.project.autilearner.home

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.squareup.picasso.Picasso
import stcet.project.autilearner.R
import stcet.project.autilearner.learn_and_play.LearnAndPlayActivity
import stcet.project.autilearner.learn_emotion.LearnEmotionActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickListener()
    }

    private fun clickListener() {
        val learnEmotionCard = findViewById<androidx.cardview.widget.CardView>(R.id.learn_emotion)
        val learnAndPlayCard = findViewById<androidx.cardview.widget.CardView>(R.id.learn_and_play)
        // variable for lets have fun
        // variable for play music

        learnEmotionCard.setOnClickListener {
            openLearnEmotionActivity() }

        learnAndPlayCard.setOnClickListener {
            openLearnAndPlayActivity()
        }
    }

    private fun openLearnEmotionActivity() {
        startActivity(Intent(this@MainActivity, LearnEmotionActivity::class.java))
    }

    private fun openLearnAndPlayActivity() {
        startActivity(Intent(this@MainActivity, LearnAndPlayActivity::class.java))
    }

    // function for lets have fun

    // function for play music
}
