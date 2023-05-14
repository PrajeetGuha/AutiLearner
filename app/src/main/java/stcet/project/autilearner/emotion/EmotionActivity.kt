package stcet.project.autilearner.emotion

import android.app.ProgressDialog
import android.graphics.Color
import android.media.MediaPlayer
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import stcet.project.autilearner.R
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CountDownLatch


class EmotionActivity : AppCompatActivity() {

    private var firestore = FirebaseFirestore.getInstance()
    private var collectionReference = firestore.collection("learn_emotion")
    private val NUMBER_OF_QUESTIONS = 5
    private lateinit var contentLayout : LinearLayout
    private lateinit var cardLayout : View
    private var randomSet = mutableMapOf<String,List<String>>()
    private lateinit var loadingScreen : ProgressBar
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emotion)
        contentLayout = findViewById<LinearLayout>(R.id.contentLayout)
        cardLayout = LayoutInflater.from(this).inflate(R.layout.emotion_card_layout,null)
        loadingScreen = findViewById<ProgressBar>(R.id.loading_progress)

        loadingScreen.visibility = View.VISIBLE
        lifecycleScope.launch {
            initialDataAccess()
        }
        loadingScreen.visibility = View.INVISIBLE
//        Log.d("FLAG", randomSet.toString())
//        for((key,value) in randomSet.entries){
//            Log.d("FLAG", key)
//            loadingScreen.visibility = View.VISIBLE
//            lifecycleScope.launch {
//                performUIUpdate(key,value as List<String>)
//            }
//            loadingScreen.visibility = View.INVISIBLE
//            break
//        }
    }

    private suspend fun initialDataAccess(){
        val listEntries = mutableMapOf<String,String>()
        collectionReference.get().addOnSuccessListener { querySnapshot ->
            for(document in querySnapshot){
                val data = document.getString("label")
                if (data != null) {
                    listEntries[document.id] = data
                }
            }
            count = listEntries.size
//            Log.d("Entries",listEntries.toString())
            generateRandomSet(listEntries,NUMBER_OF_QUESTIONS)
        }
    }

    private fun generateRandomSet(listOptions : MutableMap<String,String>, count : Int){
        val chosenDocuments = listOptions.keys.shuffled().take(count)
        val randomSet = mutableMapOf<String,List<String>>()
        for(k in chosenDocuments){
            val temp = mutableListOf<String>()
            temp.add(listOptions[k] as String)
            temp.addAll(listOptions.values.apply { remove(temp[0]) }.shuffled().take(3))
            randomSet[k] = temp
        }
        performUIUpdate(randomSet,0)
//        Log.d("RANDOM SET",randomSet.toString()
    }

    private fun performUIUpdate(randomSet : MutableMap<String,List<String>>, questionNumber : Int){
        contentLayout.removeAllViews()
        contentLayout.addView(cardLayout)
        val mediaPlayerForCorrect = MediaPlayer.create(this, R.raw.correct)
        val mediaPlayerForWrong = MediaPlayer.create(this, R.raw.wrong)
        val documentId = randomSet.keys.toList()[questionNumber]
        var options = randomSet[documentId]
        collectionReference.document(documentId).get().addOnSuccessListener { querySnapshot ->
            Picasso.get().load(querySnapshot.data?.get("image") as String).error(com.google.android.material.R.drawable.mtrl_ic_error).resize(150,150).into(cardLayout.findViewById<ImageView>(R.id.Image))
            val correct = options?.get(0)
            options = options?.shuffled()
            val option1 = contentLayout.findViewById<Button>(R.id.Option1)
            val option2 = contentLayout.findViewById<Button>(R.id.Option2)
            val option3 = contentLayout.findViewById<Button>(R.id.Option3)
            val option4 = contentLayout.findViewById<Button>(R.id.Option4)

            option1.isEnabled = true
            option2.isEnabled = true
            option3.isEnabled = true
            option4.isEnabled = true
            option1.setBackgroundColor(Color.TRANSPARENT)
            option2.setBackgroundColor(Color.TRANSPARENT)
            option3.setBackgroundColor(Color.TRANSPARENT)
            option4.setBackgroundColor(Color.TRANSPARENT)

            val listOptionButtons = listOf(option1,option2,option3,option4)
//            Log.d("CHECK")
            for(i in 0..3){
//                Log.d("DEBUG",options[i])
                listOptionButtons[i].text = options?.get(i) ?: ""
                if ((options?.get(i) ?: "") == correct){
                    listOptionButtons[i].setOnClickListener {
                        listOptionButtons[i].setBackgroundColor(Color.GREEN)
                        option1.isEnabled = false
                        option2.isEnabled = false
                        option3.isEnabled = false
                        option4.isEnabled = false
                        mediaPlayerForCorrect.start()
                        mediaPlayerForCorrect.setOnCompletionListener {
                            if (questionNumber+1 == NUMBER_OF_QUESTIONS){

                            }
                            else{
                                performUIUpdate(randomSet,questionNumber+1)
                            }
                        }
                    }
                }
                else{
                    listOptionButtons[i].setOnClickListener {
                        listOptionButtons[i].setBackgroundColor(Color.RED)
                        option1.isEnabled = false
                        option2.isEnabled = false
                        option3.isEnabled = false
                        option4.isEnabled = false
                        mediaPlayerForWrong.start()
                        mediaPlayerForWrong.setOnCompletionListener {
                            if (questionNumber+1 == NUMBER_OF_QUESTIONS){

                            }
                            else{
                                performUIUpdate(randomSet,questionNumber+1)
                            }
                        }
                    }
                }
            }
        }
    }
}