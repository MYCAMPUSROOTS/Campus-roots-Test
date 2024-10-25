package com.example.campusrootsinternapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.campusrootsinternapp.adapter.Adapter
import com.example.campusrootsinternapp.base.BaseFragment

class Fragment1 : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView.apply {
//            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
//            adapter = Adapter(courseList)
//            setRecycledViewPool(recycledViewPool)
//        }
    }

    companion object {

    }
}