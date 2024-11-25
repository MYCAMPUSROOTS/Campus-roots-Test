package com.example.campusrootsinternapp.enrollment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.campusrootsinternapp.databinding.ActivityEnrollmentBinding
import com.example.campusrootsinternapp.enrollment.data.EnrollmentRepository
import com.example.campusrootsinternapp.enrollment.data.model.CompleteEnrollmentRequest
import com.example.campusrootsinternapp.enrollment.data.model.InitiateEnrollmentRequest

class EnrollmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnrollmentBinding
    private lateinit var viewModel: EnrollmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnrollmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, EnrollmentViewModelFactory(EnrollmentRepository())).get(EnrollmentViewModel::class.java)

        binding.initiateEnrollmentButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            val username = binding.usernameEditText.text.toString()

            val request = InitiateEnrollmentRequest(email, firstName, lastName, username)
            viewModel.initiateEnrollment(request)
        }

        binding.completeEnrollmentButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            val otp = binding.otpEditText.text.toString()
            val ambassador = binding.ambassadorEditText.text.toString()
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            val request = CompleteEnrollmentRequest(email, firstName, lastName, otp, ambassador, username, password)
            viewModel.completeEnrollment(request)
        }
    }
}