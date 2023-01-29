package stcet.project.autilearner.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import stcet.project.autilearner.R
import stcet.project.autilearner.model.Category
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.squareup.picasso.Picasso

class CategoriesAdapter(options: FirestoreRecyclerOptions<Category>) : FirestoreRecyclerAdapter<Category, CategoriesAdapter.ViewHolder>(options) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageView = view.findViewById<ImageView>(R.id.categoryImage)
        val textView = view.findViewById<TextView>(R.id.categoryText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Category) {
        holder.textView.text = model.name
        model.image?.let { Log.d("DATA", it) }

        Picasso.get().load(model.image).error(com.google.android.material.R.drawable.mtrl_ic_error).resize(150,150).into(holder.imageView);
    }

}