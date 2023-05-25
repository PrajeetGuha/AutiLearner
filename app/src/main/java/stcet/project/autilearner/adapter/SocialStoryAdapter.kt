package stcet.project.autilearner.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import stcet.project.autilearner.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.squareup.picasso.Picasso
import stcet.project.autilearner.model.Story

class SocialStoryAdapter(options: FirestoreRecyclerOptions<Story>,
) : FirestoreRecyclerAdapter<Story, SocialStoryAdapter.ViewHolder>(options) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageView: ImageView = view.findViewById<ImageView>(R.id.story_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.social_story_image,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Story) {
        model.image?.let { Log.d("DATA", it) }
        Picasso.get().load(model.image).error(com.google.android.material.R.drawable.mtrl_ic_error).resize(900,900).into(holder.imageView)

    }
}