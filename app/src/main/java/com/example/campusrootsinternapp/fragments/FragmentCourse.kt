package com.example.campusrootsinternapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.campusrootsinternapp.R
import com.example.campusrootsinternapp.base.BaseFragment

class FragmentCourse : BaseFragment() {

    private var courseName: String? = null
    private var courseTitle: String? = null
    private var courseLecturer: String? = null
    private var courseChannels: Int? = null
    private var isActive: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            courseName = it.getString("courseName")
            courseTitle = it.getString("courseTitle")
            courseLecturer = it.getString("courseLecturer")
            courseChannels = it.getInt("courseChannels")
            isActive = it.getBoolean("isActive")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)


        val courseNameTextView = view.findViewById<TextView>(R.id.courseName)
        val courseTitleTextView = view.findViewById<TextView>(R.id.courseTitle)
        val courseLecturerTextView = view.findViewById<TextView>(R.id.courseLecturer)
        val courseChannelsTextView = view.findViewById<TextView>(R.id.courseChannels)
        val courseActiveTextView = view.findViewById<TextView>(R.id.courseActive)

        courseNameTextView?.text = courseName
        courseTitleTextView?.text = courseTitle
        courseLecturerTextView?.text = courseLecturer
        courseChannelsTextView?.text = courseChannels.toString() + " Channels"

        if (isActive == true) {
            courseActiveTextView?.text = "Active"
        } else {
            courseActiveTextView?.text = "Inactive"
        }
        return view
    }
}