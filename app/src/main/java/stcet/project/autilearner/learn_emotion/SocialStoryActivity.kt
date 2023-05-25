package stcet.project.autilearner.learn_emotion

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import stcet.project.autilearner.R
import stcet.project.autilearner.adapter.SocialStoryAdapter
import stcet.project.autilearner.model.Story


class SocialStoryActivity : AppCompatActivity() {

    private val storyCollection = FirebaseFirestore.getInstance().collection("social_story")
    private lateinit var socialStoryAdapter : SocialStoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_story)
        setRecyclerView()
    }
    private fun setRecyclerView(){
        val query = storyCollection.orderBy("id", Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<Story>()
            .setQuery(query,Story::class.java)
            .build()
        val recyclerView = findViewById<RecyclerView>(R.id.storyRecyclerView)
        socialStoryAdapter = SocialStoryAdapter(options)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = socialStoryAdapter
    }

    override fun onStart() {
        super.onStart()
        socialStoryAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        socialStoryAdapter.stopListening()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this, LearnEmotionActivity::class.java)
        startActivity(intent)
    }
}

