package com.example.leaarn_kotlin_for_android.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaarn_kotlin_for_android.R

class SmallImagesAdapter(private val smallImages: List<String>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<SmallImagesAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_small_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SmallImagesAdapter.ViewHolder, position: Int) {
        val imageResId = smallImages[position]
        //holder.imageView.setImageResource(imageResId)
        Glide.with(holder.itemView.context)
            .load(imageResId)
            .into(holder.imageView)


        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return smallImages.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.smallImageView)
    }

}