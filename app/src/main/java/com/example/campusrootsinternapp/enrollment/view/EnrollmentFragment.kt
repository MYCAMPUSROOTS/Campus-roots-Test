package com.example.campusrootsinternapp.enrollment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.campusrootsinternapp.databinding.FragmentEnrollmentBinding
import com.example.campusrootsinternapp.enrollment.data.EnrollmentRepository
import com.example.campusrootsinternapp.enrollment.data.model.CompleteEnrollmentRequest
import com.example.campusrootsinternapp.enrollment.data.model.InitiateEnrollmentRequest

class EnrollmentFragment : Fragment() {
    private lateinit var binding: FragmentEnrollmentBinding
    private lateinit var viewModel: EnrollmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEnrollmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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