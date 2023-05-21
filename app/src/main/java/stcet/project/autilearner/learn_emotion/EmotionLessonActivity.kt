package stcet.project.autilearner.learn_emotion

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import stcet.project.autilearner.R
import stcet.project.autilearner.helper.AuthO

class EmotionLessonActivity : AppCompatActivity() {

    private var firestore = FirebaseFirestore.getInstance()
    private lateinit var collectionReference : CollectionReference
    private lateinit var contentLayout : LinearLayout
    private lateinit var cardLayout : View
    private lateinit var loadingScreen : ProgressBar
    private var count = 0
    private var correctAnswers = 0
    private val NUMBER_OF_QUESTIONS = 7
    private var popupWindow : PopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
//        val user = AuthO().getUser()
//        if(user == null){
//            val splash = Intent(this, SplashActivity::class.java)
//            startActivity(splash)
//        }
        var collectionPath = when(intent.getStringExtra("lesson_number")){
            "1" -> "emotion_lesson1"
            "2" -> "emotion_lesson2"
            "3" -> "emotion_lesson3"
            "4" -> "emotion_lesson4"
            else -> ""
        }
        collectionReference = firestore.collection(collectionPath)
        contentLayout = findViewById<LinearLayout>(R.id.content_layout)
        cardLayout = LayoutInflater.from(this).inflate(R.layout.option_card,null)
//        loadingScreen = findViewById<ProgressBar>(R.id.emotion_loadingProgress)
//        loadingScreen.visibility = View.VISIBLE
        lifecycleScope.launch {
            initialDataAccess()
        }
//        loadingScreen.visibility = View.INVISIBLE
    }

    private suspend fun initialDataAccess() {
        val listEntries = mutableMapOf<String,String>()
        collectionReference.get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                val data = document.getString("label")
                if (data != null) {
                    listEntries[document.id] = data
//                    Log.d("DEBUG",listEntries.toString())
                }
            }
            count = listEntries.size
            generateRandomSet(listEntries, NUMBER_OF_QUESTIONS)
        }
    }

    private fun generateRandomSet(listOptions: MutableMap<String, String>, count : Int) {
        val chosenDocuments = listOptions.keys.shuffled().take(count)
        val randomSet = mutableMapOf<String,List<String>>()
        var temp = mutableListOf<String>()
        val listOfAnswers = mutableListOf<String>(*listOptions.values.toTypedArray())
        for (k in chosenDocuments) {
            temp.add(listOptions[k] as String)
            listOfAnswers.remove(listOptions[k])
            temp.addAll(listOfAnswers.shuffled().take(3))
            listOfAnswers.add(listOptions[k] as String)
//            Log.d("DEBUG",listOptions.toString())
            randomSet[k] = listOf(*temp.toTypedArray())
            temp.clear()
        }
//        Log.d("DEBUG",randomSet.toString())
        performUIUpdate(randomSet, 0)
    }

    private fun performUIUpdate(randomSet : MutableMap<String,List<String>>, questionNumber : Int) {
        contentLayout.removeAllViews()
        contentLayout.addView(cardLayout)
        findViewById<TextView>(R.id.lesson_heading).text = resources.getString(R.string.emotion_lesson_heading)
        val mediaPlayerForCorrect = MediaPlayer.create(this, R.raw.correct)
        val mediaPlayerForWrong = MediaPlayer.create(this, R.raw.wrong)
        val documentId = randomSet.keys.toList()[questionNumber]
        var options = randomSet[documentId]
        collectionReference.document(documentId).get().addOnSuccessListener { querySnapshot ->
            Picasso.get().load(querySnapshot.data?.get("image") as String).error(com.google.android.material.R.drawable.mtrl_ic_error).resize(150,150).into(cardLayout.findViewById<ImageView>(R.id.image))
            val correct = options?.get(0)
            options = options?.shuffled()
            val option1 = contentLayout.findViewById<Button>(R.id.option1)
            val option2 = contentLayout.findViewById<Button>(R.id.option2)
            val option3 = contentLayout.findViewById<Button>(R.id.option3)
            val option4 = contentLayout.findViewById<Button>(R.id.option4)
            val toastLayoutView = findViewById<ViewGroup>(R.id.toastLayout)

            option1.isEnabled = true
            option2.isEnabled = true
            option3.isEnabled = true
            option4.isEnabled = true
            option1.setBackgroundColor(Color.TRANSPARENT)
            option2.setBackgroundColor(Color.TRANSPARENT)
            option3.setBackgroundColor(Color.TRANSPARENT)
            option4.setBackgroundColor(Color.TRANSPARENT)
            toastLayoutView.setBackgroundColor(Color.TRANSPARENT)

            val listOptionButtons = listOf(option1,option2,option3,option4)
            for (i in 0..3) {
                toastLayoutView.removeAllViews()
                listOptionButtons[i].text = options?.get(i) ?: ""
                if ((options?.get(i) ?: "") == correct) {
                    listOptionButtons[i].setOnClickListener {
                        listOptionButtons[i].setBackgroundColor(resources.getColor(R.color.green))
                        option1.isEnabled = false
                        option2.isEnabled = false
                        option3.isEnabled = false
                        option4.isEnabled = false
                        val toastLayout = LayoutInflater.from(this).inflate(R.layout.custom_toast, null)
                        toastLayoutView.setBackgroundColor(resources.getColor(R.color.green))
                        correctAnswers += 1
                        toastLayoutView.addView(toastLayout)
                        toastLayoutView.findViewById<TextView>(R.id.toast_text).text = getString(R.string.correct_answer, correct)
                        toastLayoutView.findViewById<ImageView>(R.id.toast_image).setImageResource(R.drawable.correct)
                        mediaPlayerForCorrect.start()
                        mediaPlayerForCorrect.setOnCompletionListener {
                            if (questionNumber+1 == NUMBER_OF_QUESTIONS) {
                                showResult()
                            }
                            else {
                                performUIUpdate(randomSet, questionNumber+1)
                            }
                        }
                    }
                }
                else {
                    listOptionButtons[i].setOnClickListener {
                        listOptionButtons[i].setBackgroundColor(resources.getColor(R.color.orange))
                        option1.isEnabled = false
                        option2.isEnabled = false
                        option3.isEnabled = false
                        option4.isEnabled = false
                        val toastLayout = LayoutInflater.from(this).inflate(R.layout.custom_toast, null)
                        toastLayoutView.setBackgroundColor(resources.getColor(R.color.orange))
                        toastLayoutView.addView(toastLayout)
                        toastLayoutView.findViewById<TextView>(R.id.toast_text).text = getString(R.string.wrong_answer, correct)
                        toastLayoutView.findViewById<ImageView>(R.id.toast_image).setImageResource(R.drawable.wrong)
                        mediaPlayerForWrong.start()
                        mediaPlayerForWrong.setOnCompletionListener {
                            if (questionNumber+1 == NUMBER_OF_QUESTIONS) {

                                showResult()
                            }
                            else {
                                performUIUpdate(randomSet,questionNumber+1)
                            }
                        }
                    }
                }
            }
        }
    }
    
    private fun showResult() {
//        Log.d("CHECK", UserDataManager.getInstance().getData().toString())
//        val data = UserDataManager.getInstance().getData().toMutableMap()
//        val presentStatus = (UserDataManager.getInstance().getData()["Learn_Emotion"] as List<*>).toMutableList()
//        presentStatus[intent.getStringExtra("lesson_number") as Int] = correctAnswers == NUMBER_OF_QUESTIONS
//        data["Learn_Emotion"] = presentStatus
//        UserDataManager.getInstance().setData(data)
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("total_correct", correctAnswers.toString())
        intent.putExtra("total_questions",NUMBER_OF_QUESTIONS.toString())
        startActivity(intent)
    }

    override fun onBackPressed() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popout_layout, null)

        popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val color = TypedValue()
        theme.resolveAttribute(android.R.attr.colorBackground,color,true)
        val backgroundDrawable = GradientDrawable()
        backgroundDrawable.setColor(color.data)
        findViewById<View>(R.id.background_overlay).visibility = View.VISIBLE
        popupWindow?.setBackgroundDrawable(backgroundDrawable)
        cardLayout.findViewById<ImageView>(R.id.image).setColorFilter(Color.argb(150,0,0,0))
        popupWindow?.isOutsideTouchable = false
        popupWindow?.isFocusable = true
        popupWindow?.isTouchable = true
        popupView.rootView.isFocusable = false
        popupView.rootView.isFocusableInTouchMode = false

        popupView.findViewById<Button>(R.id.moveHomeButton).setOnClickListener {
            popupWindow?.dismiss()
            popupWindow = null
            super.onBackPressed()
        }

        popupView.findViewById<Button>(R.id.continueButton).setOnClickListener {
            popupWindow?.dismiss()
            popupWindow = null
            cardLayout.findViewById<ImageView>(R.id.image).setColorFilter(Color.TRANSPARENT)
            popupView.rootView.isFocusable = true
            popupView.rootView.isFocusableInTouchMode = true
            findViewById<View>(R.id.background_overlay).visibility = View.INVISIBLE
        }

        popupWindow?.showAtLocation(popupView, Gravity.CENTER, 0,0)
    }

    override fun onStart() {
        super.onStart()
//        val user = AuthO().getUser()
//        if(user == null){
//            val splash = Intent(this, SplashActivity::class.java)
//            startActivity(splash)
//        }
    }
}