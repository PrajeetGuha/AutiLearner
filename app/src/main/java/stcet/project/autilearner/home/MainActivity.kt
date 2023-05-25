package stcet.project.autilearner.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import stcet.project.autilearner.R
import stcet.project.autilearner.learn_and_play.LearnAndPlayActivity
import stcet.project.autilearner.learn_emotion.LearnEmotionActivity
import stcet.project.autilearner.lets_have_fun.LetsHaveFunActivity
import stcet.project.autilearner.play_music.PlayMusicActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickListener()
    }

    private fun clickListener() {
        val learnEmotionCard = findViewById<androidx.cardview.widget.CardView>(R.id.learn_emotion)
        val learnAndPlayCard = findViewById<androidx.cardview.widget.CardView>(R.id.learn_and_play)
        val playMusicCard = findViewById<androidx.cardview.widget.CardView>(R.id.play_music)
        val letsHaveFunCard = findViewById<androidx.cardview.widget.CardView>(R.id.lets_have_fun)
        learnEmotionCard.setOnClickListener {
            openLearnEmotionActivity()
        }
        learnAndPlayCard.setOnClickListener {
            openLearnAndPlayActivity()
        }
        playMusicCard.setOnClickListener {
            openPlayMusicActivity()
        }
        letsHaveFunCard.setOnClickListener {
            openLetsHaveFunActivity()
        }
    }

    private fun openLetsHaveFunActivity() {
        startActivity(Intent(this@MainActivity, LetsHaveFunActivity::class.java))
    }

    private fun openLearnEmotionActivity() {
        startActivity(Intent(this@MainActivity, LearnEmotionActivity::class.java))
    }

    private fun openLearnAndPlayActivity() {
        startActivity(Intent(this@MainActivity, LearnAndPlayActivity::class.java))
    }

    private fun openPlayMusicActivity() {
        startActivity(Intent(this@MainActivity, PlayMusicActivity::class.java))
    }

}
