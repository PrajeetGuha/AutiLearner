package stcet.project.autilearner.learn_emotion

import android.content.Intent
import android.os.Bundle

import android.widget.Button
import android.widget.FrameLayout.LayoutParams
import android.widget.ImageSwitcher

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import stcet.project.autilearner.R


class SocialStoryActivity : AppCompatActivity() {

    private lateinit var imageSwitcher: ImageSwitcher
    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button

    private var imageUrls: MutableList<String> = mutableListOf()
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_story)
        imageSwitcher = findViewById(R.id.imageSwitcher)
        btnPrevious = findViewById(R.id.prev)
        btnNext = findViewById(R.id.next)

        imageSwitcher.setFactory {
            ImageView(this@SocialStoryActivity).apply {
                scaleType = ImageView.ScaleType.FIT_CENTER
                layoutParams = ImageSwitcher.LayoutParams(
                    ImageSwitcher.LayoutParams.MATCH_PARENT,
                    ImageSwitcher.LayoutParams.MATCH_PARENT
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onBackPressed() {
        val intent = Intent(this, LearnEmotionActivity::class.java)
    }
}