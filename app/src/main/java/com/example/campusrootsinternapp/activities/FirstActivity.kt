package com.example.campusrootsinternapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.campusrootsinternapp.R

class FirstActivity : AppCompatActivity() {

    companion object {
        fun newIntent(requireContext: Context, email: String, password: String): Intent {
            val intent = Intent(requireContext, FirstActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        // Do something with the email and password
    }
}