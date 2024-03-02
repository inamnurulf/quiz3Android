package com.example.quiz3android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz3android.R // Make sure to use your actual R class package

class PhotoAdapter(private val photoList: List<Photo>) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentPhoto = photoList[position]

        holder.titleTextView.text = currentPhoto.title
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}
