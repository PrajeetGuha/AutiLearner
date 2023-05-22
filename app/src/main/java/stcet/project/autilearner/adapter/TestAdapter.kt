package stcet.project.autilearner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import stcet.project.autilearner.R

class TestAdapter(dataset : List<String>) : RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    val data = dataset

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val textView = view.findViewById<TextView>(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.social_story_image,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }


}