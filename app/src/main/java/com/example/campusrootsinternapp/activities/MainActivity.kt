package com.example.campusrootsinternapp.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.campusrootsinternapp.HomeFragment
import com.example.campusrootsinternapp.R
import com.example.campusrootsinternapp.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val fragmentTransaction: FragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.show_from_bottom, R.anim.slide_out_bottom)
        fragmentTransaction.replace(
            R.id.content,
            HomeFragment() // replace with your fragment
        )
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()

    }

}