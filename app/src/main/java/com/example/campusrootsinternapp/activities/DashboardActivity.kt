package com.example.campusrootsinternapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.campusrootsinternapp.DashboardFragment
import com.example.campusrootsinternapp.R
import com.example.campusrootsinternapp.base.BaseActivity
import com.example.campusrootsinternapp.base.BaseFragment

class DashboardActivity : BaseActivity() {
    val baseFragment = lazy {
        listOf<BaseFragment>(DashboardFragment())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, DashboardFragment.newInstance())
        fragmentTransaction.commit()

        initFragNavController(this, baseFragment.value, TAG, supportFragmentManager, R.id.content,savedInstanceState)
    }

    companion object{
        val TAG = "Intro Activity"

    }
}