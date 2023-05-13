package stcet.project.autilearner.emotion

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import stcet.project.autilearner.R
import java.util.*


class EmotionActivity : AppCompatActivity() {

    private var firestore = FirebaseFirestore.getInstance()
    private var collectionReference = firestore.collection("learn_emotion")
    private val NUMBER_OF_QUESTIONS = 5
    private val contentLayout = findViewById<LinearLayout>(R.id.contentLayout)
    private val cardLayout = LayoutInflater.from(this).inflate(R.layout.emotion_card_layout,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emotion)
        var listOptions = listOf<String>()
        var count = 0
        val loadingScreen = findViewById<ProgressBar>(R.id.loading_progress)

        loadingScreen.visibility = View.VISIBLE
        lifecycleScope.launch {
            listOptions = initialDataAccess()
            count = listOptions.size
        }
        loadingScreen.visibility = View.INVISIBLE

        loadingScreen.visibility = View.VISIBLE
        for(questionNumber in 0..count){
            lifecycleScope.launch {
                performUIUpdate(listOptions,NUMBER_OF_QUESTIONS,questionNumber)
                loadingScreen.visibility = View.INVISIBLE
            }
        }
    }

    private suspend fun initialDataAccess() : List<String>{
        val listEntries = mutableListOf<String>()
        collectionReference.get().addOnSuccessListener { querySnapshot ->
            for(document in querySnapshot){
                val data = document.getString("label")
                if (data != null) {
                    listEntries.add(data)
                }
            }
        }
        return listEntries
    }

    private fun generateRandomNumbers(min: Int, max: Int, count: Int): List<Int> {
        val random = Random()
        return List(count){ random.nextInt(max - min + 1) + min}

    }

    private suspend fun performUIUpdate(listOptions : List<String>, numOfQuestions : Int, questionNumber : Int){

    }
}