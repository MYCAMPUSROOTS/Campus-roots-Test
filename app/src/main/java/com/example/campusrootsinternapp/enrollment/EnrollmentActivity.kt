package com.example.campusrootsinternapp.enrollment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.campusrootsinternapp.R

class EnrollmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enrollment)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EnrollmentFragment.newInstance())
                .commitNow()
        }
    }
}