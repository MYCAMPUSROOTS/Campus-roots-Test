package com.example.campusrootsinternapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.campusrootsinternapp.R
import com.example.campusrootsinternapp.model.CourseItem

class Adapter(private val courseList : List<CourseItem>) : RecyclerView.Adapter<Adapter.ViewHolder>(){


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val courseNameTxt : TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(parent.context)
            .inflate(R.layout.course_item,parent,false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = courseList[position]
        holder.courseNameTxt.text = currentItem.courseName
    }
}
