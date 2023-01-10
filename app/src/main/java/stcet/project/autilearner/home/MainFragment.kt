package stcet.project.autilearner.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import stcet.project.autilearner.R
import stcet.project.autilearner.adapter.CategoriesAdapter
import stcet.project.autilearner.dataloader.FirestoreDataLoader
import stcet.project.autilearner.model.Category

class MainFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.main_recyclerview)
//        val database = FirestoreDataLoader()
        recyclerView.adapter = CategoriesAdapter(mutableListOf<Category>())
    }
}