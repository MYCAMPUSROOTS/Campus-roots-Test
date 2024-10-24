package com.example.campusrootsinternapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.campusrootsinternapp.DashboardFragment
import com.example.campusrootsinternapp.R
import com.example.campusrootsinternapp.base.BaseActivity

class DashboardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, DashboardFragment.newInstance())
        fragmentTransaction.commit()
    }
}