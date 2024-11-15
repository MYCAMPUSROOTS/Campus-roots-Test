package com.example.campusrootsinternapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.campusrootsinternapp.R
import com.example.campusrootsinternapp.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class Fragment4 : BaseFragment() {

    private lateinit var backButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backButton = view.findViewById(R.id.back_button_fragment4)
        backButton.setOnClickListener {
            mFragmentNavigation.popFragment()
        }
    }
}