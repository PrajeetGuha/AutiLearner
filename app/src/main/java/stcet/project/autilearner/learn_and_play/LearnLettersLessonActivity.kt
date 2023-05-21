package stcet.project.autilearner.learn_and_play

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import stcet.project.autilearner.R

class LearnLettersLessonActivity : AppCompatActivity() {

    private lateinit var collectionReference : CollectionReference
    private val letterSequence = "ABCDEFGIJKLMNOPQRSTUVWXYZ"
    private lateinit var question : TextView
    private lateinit var option1 : ImageButton
    private lateinit var option2 : ImageButton
    private lateinit var option3 : ImageButton
    private lateinit var option4 : ImageButton
    private lateinit var toast : LinearLayout
    private var correctAnswers = 0
    private lateinit var mediaPlayerForCorrect : MediaPlayer
    private lateinit var mediaPlayerForWrong : MediaPlayer
    private var popupWindow : PopupWindow? = null
    private val NUMBER_OF_QUESTIONS = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learnletters)

        collectionReference = FirebaseFirestore.getInstance().collection("learnplay_learnAZ")
        question = findViewById(R.id.question)
        option1=findViewById(R.id.option1)
        option2=findViewById(R.id.option2)
        option3=findViewById(R.id.option3)
        option4=findViewById(R.id.option4)
        toast=findViewById(R.id.toast_layout)
        mediaPlayerForCorrect = MediaPlayer.create(this, R.raw.correct)
        mediaPlayerForWrong = MediaPlayer.create(this, R.raw.wrong)

        lifecycleScope.launch{
            performUIUpdate(0)
        }
    }

    private suspend fun performUIUpdate(questionNumber : Int) {
        val optionSet = listOf<ImageButton>(option1,option2,option3,option4).shuffled()
        option1.isEnabled = true
        option2.isEnabled = true
        option3.isEnabled = true
        option4.isEnabled = true
        option1.setBackgroundColor(Color.TRANSPARENT)
        option2.setBackgroundColor(Color.TRANSPARENT)
        option3.setBackgroundColor(Color.TRANSPARENT)
        option4.setBackgroundColor(Color.TRANSPARENT)
        toast.visibility = View.INVISIBLE

        collectionReference
            .whereEqualTo("letter",letterSequence[questionNumber].toString())
            .get()
            .addOnSuccessListener { querySnapshot ->
                val choosenDocument : MutableMap<String,Any?>
                val documents = querySnapshot.documents
                if(documents.isNotEmpty()){
                    choosenDocument = documents.shuffled()[0].data as MutableMap<String,Any?>
                }
                else{
                    choosenDocument = mutableMapOf()
                }
                collectionReference
                    .whereNotEqualTo("letter",letterSequence[questionNumber].toString())
                    .get()
                    .addOnSuccessListener { innerQuerySnapshot ->
                        val listOptions = mutableListOf<String>()
                        for(document in innerQuerySnapshot.documents.shuffled().take(3)){
                            listOptions.add(document.getString("image") as String)
                        }
                        question.text = letterSequence[questionNumber].toString()
                        Picasso
                            .get()
                            .load(choosenDocument["image"] as String)
//                            .transform(TransparentBackgroundTransformation())
                            .error(com.google.android.material.R.drawable.mtrl_ic_error)
                            .resize(150,150)
                            .into(optionSet[0])
//                        Log.d("CHECK",optionSet.count().toString())
                        for(i in 1..3){
                            Picasso
                                .get()
                                .load(listOptions[i-1])
//                                .transform(TransparentBackgroundTransformation())
                                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                                .resize(150,150)
                                .into(optionSet[i])
                        }
                        setRightAnswerListener(choosenDocument["name"] as String,optionSet,questionNumber)
                        setWrongAnswerListener(choosenDocument["name"] as String,optionSet,questionNumber)
                    }
            }
    }

    private fun setRightAnswerListener(rightAnswer : String, optionSet : List<ImageButton>,questionNumber: Int){
        optionSet[0].setOnClickListener {
            toast.setBackgroundColor(resources.getColor(R.color.green))
            optionSet[0].setBackgroundColor(resources.getColor(R.color.green))
            toast.visibility = View.VISIBLE
            correctAnswers += 1
            toast.findViewById<TextView>(R.id.toast_text).text = getString(R.string.correct_answer,rightAnswer)
            toast.findViewById<ImageView>(R.id.toast_image).setImageResource(R.drawable.correct)
            option1.isEnabled = false
            option2.isEnabled = false
            option3.isEnabled = false
            option4.isEnabled = false
            mediaPlayerForCorrect.start()
            mediaPlayerForCorrect.setOnCompletionListener {
                if (questionNumber+1 == NUMBER_OF_QUESTIONS) {
                    showResult()
                }
                else {
                    lifecycleScope.launch{
                        performUIUpdate(questionNumber+1)
                    }
                }
            }
        }

    }
    private fun setWrongAnswerListener(rightAnswer : String, optionSet : List<ImageButton>,questionNumber: Int) {
        for (i in 1..3) {
            optionSet[i].setOnClickListener {
                toast.setBackgroundColor(resources.getColor(R.color.orange))
                optionSet[0].setBackgroundColor(resources.getColor(R.color.orange))
                toast.visibility = View.VISIBLE
                toast.findViewById<TextView>(R.id.toast_text).text =
                    getString(R.string.wrong_answer, rightAnswer)
                toast.findViewById<ImageView>(R.id.toast_image).setImageResource(R.drawable.wrong)
                option1.isEnabled = false
                option2.isEnabled = false
                option3.isEnabled = false
                option4.isEnabled = false
                mediaPlayerForWrong.start()
                mediaPlayerForWrong.setOnCompletionListener {
                    if (questionNumber + 1 == NUMBER_OF_QUESTIONS) {
                        showResult()
                    } else {
                        lifecycleScope.launch {
                            performUIUpdate(questionNumber + 1)
                        }
                    }
                }
            }

        }
    }

    private fun showResult() {
        val intent = Intent(this, LearnNPlayResultActivity::class.java)
        intent.putExtra("total_correct", correctAnswers.toString())
        intent.putExtra("total_questions",NUMBER_OF_QUESTIONS)
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
        option1.setColorFilter(Color.argb(150,0,0,0))
        option2.setColorFilter(Color.argb(150,0,0,0))
        option3.setColorFilter(Color.argb(150,0,0,0))
        option4.setColorFilter(Color.argb(150,0,0,0))
        popupWindow?.setBackgroundDrawable(backgroundDrawable)
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
            option1.setColorFilter(Color.TRANSPARENT)
            option2.setColorFilter(Color.TRANSPARENT)
            option3.setColorFilter(Color.TRANSPARENT)
            option4.setColorFilter(Color.TRANSPARENT)
            popupWindow = null
            popupView.rootView.isFocusable = true
            popupView.rootView.isFocusableInTouchMode = true
            findViewById<View>(R.id.background_overlay).visibility = View.INVISIBLE
        }

        popupWindow?.showAtLocation(popupView, Gravity.CENTER, 0,0)
    }

}