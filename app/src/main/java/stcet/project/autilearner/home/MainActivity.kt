package stcet.project.autilearner.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import stcet.project.autilearner.R
import stcet.project.autilearner.adapter.CategoriesAdapter
import stcet.project.autilearner.dataloader.FirestoreDataLoader
import stcet.project.autilearner.helper.AuthO
import stcet.project.autilearner.model.Category

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.category_recycler)
        val dataLoader = FirestoreDataLoader()
        
    }
}