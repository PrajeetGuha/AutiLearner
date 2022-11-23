package stcet.project.autilearner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_letsBegin).setOnClickListener {
            val intentSignup = Intent(this,SignUpActivity::class.java)
            startActivity(intentSignup)
        }
    }
}