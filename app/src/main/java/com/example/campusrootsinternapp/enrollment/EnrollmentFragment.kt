package com.example.campusrootsinternapp.enrollment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.campusrootsinternapp.R
import com.example.campusrootsinternapp.databinding.FragmentEnrollmentBinding
import com.example.campusrootsinternapp.enrollment.model.InitiateEnrollmentRequest
import com.example.campusrootsinternapp.util.observeChange

class EnrollmentFragment : Fragment() {

    companion object {
        fun newInstance() = EnrollmentFragment()
    }

    private val viewModel: EnrollmentViewModel by viewModels()
    private var _binding: FragmentEnrollmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnrollmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.enrollButton.setOnClickListener {
            var firstName = binding.firstName.text.toString()
            var lastName = binding.lastName.text.toString()
            var email = binding.email.text.toString()
            var username = binding.username.text.toString()

            var request = InitiateEnrollmentRequest(email, firstName, username, lastName)

            viewModel.registerUser(request)

            viewModel.enrollment.observeChange(viewLifecycleOwner) { enrollment ->
                println(enrollment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}