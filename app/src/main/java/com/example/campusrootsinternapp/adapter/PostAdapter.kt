package com.example.campusrootsinternapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.campusrootsinternapp.R
import com.example.campusrootsinternapp.model.PostResponse

class PostAdapter(private val postResponse: List<PostResponse>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postResponse[position]
        holder.userId.text = "User ID: " + post.userId.toString()
        holder.id.text = "ID: " + post.id.toString()
        holder.title.text = post.title
        holder.body.text = post.body
    }

    override fun getItemCount(): Int {
        return postResponse.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userId: TextView = itemView.findViewById(R.id.userId)
        val id: TextView = itemView.findViewById(R.id.id)
        val title: TextView = itemView.findViewById(R.id.title)
        val body: TextView = itemView.findViewById(R.id.body)
    }
}