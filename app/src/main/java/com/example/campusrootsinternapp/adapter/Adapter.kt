package com.example.campusrootsinternapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.campusrootsinternapp.R
import com.example.campusrootsinternapp.model.CourseItem
import com.example.campusrootsinternapp.util.SingleLiveEvent

class Adapter(private val courseList : List<CourseItem>) : RecyclerView.Adapter<Adapter.ViewHolder>(){

    val selectedCourse = SingleLiveEvent<CourseItem>()

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val courseCode : TextView = itemView.findViewById(R.id.courseCode)
        val courseTitle : TextView = itemView.findViewById(R.id.courseTitle)
        val courseLecturer : TextView = itemView.findViewById(R.id.courseLecturer)
        val courseChannels : TextView = itemView.findViewById(R.id.courseChannels)
//        val isActive : TextView = itemView.findViewById(R.id.isActive)
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
        holder.courseCode.text = currentItem.courseName
        holder.courseTitle.text = currentItem.courseTitle
        holder.courseLecturer.text = currentItem.courseLecturer
        holder.courseChannels.text = currentItem.courseChannels.toString()

//        if(currentItem.isActive) {
//            holder.isActive.visibility = View.VISIBLE
//        } else {
//            holder.isActive.visibility = View.GONE
//        }
    }
}
