package stcet.project.autilearner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import stcet.project.autilearner.R
import stcet.project.autilearner.model.Category

class CategoriesAdapter(private val dataset: List<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private lateinit var context : Context
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageView = view.findViewById<ImageView>(R.id.categoryImage)
        val textView = view.findViewById<TextView>(R.id.categoryText)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_card,parent,false)

        return ViewHolder(view)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = position.toString()
        holder.imageView.setImageResource(R.drawable.facebook_icon_2013)

//        Glide.with(context).load(dataset[position].image).into(holder.imageView)
    }

}