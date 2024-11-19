package com.example.campusrootsinternapp.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentTransaction
import com.example.campusrootsinternapp.R
import com.example.campusrootsinternapp.base.BaseActivity
import com.example.campusrootsinternapp.fragments.DashboardFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val fragmentTransaction: FragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.show_from_bottom, R.anim.slide_out_bottom)
        fragmentTransaction.replace(
            R.id.content,
            DashboardFragment() // replace with your fragment
        )
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()

    }

}