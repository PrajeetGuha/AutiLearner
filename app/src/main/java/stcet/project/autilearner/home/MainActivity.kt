package stcet.project.autilearner.home

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.squareup.picasso.Picasso
import stcet.project.autilearner.R
import stcet.project.autilearner.adapter.CategoriesAdapter
import stcet.project.autilearner.model.Category


class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val categoriesCollection = db.collection("categories")
    private val othersCollection = db.collection("others")
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
        setPhraseOfTheDay()
        
    }

    private fun setRecyclerView(){
        val query = categoriesCollection.orderBy("ID", Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<Category>()
            .setQuery(query,Category::class.java)
            .build()

        val recyclerView = findViewById<RecyclerView>(R.id.category_recycler)
        categoriesAdapter = CategoriesAdapter(options)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = categoriesAdapter

    }

    private fun setPhraseOfTheDay(){
        val imageView = findViewById<ImageView>(R.id.phraseOfTheDay)
        val documentReference = othersCollection.document(getString(R.string.phrase_of_the_day_docID))
        documentReference.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    Picasso.get()
                        .load(documentSnapshot.get("image") as String)
                        .error(com.google.android.material.R.drawable.mtrl_ic_error)
                        .resize(413,177)
                        .into(imageView)
                } else {
                    imageView.setImageResource(com.google.android.material.R.drawable.mtrl_ic_error)
                }
            }
            .addOnFailureListener { exception ->

            }

    }

    override fun onStart() {
        super.onStart()
        categoriesAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        categoriesAdapter.stopListening()
    }
}