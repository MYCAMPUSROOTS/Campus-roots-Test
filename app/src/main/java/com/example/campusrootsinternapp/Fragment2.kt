package com.example.campusrootsinternapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.campusrootsinternapp.base.BaseFragment
import com.example.campusrootsinternapp.databinding.Fragment2Binding
import com.example.campusrootsinternapp.model.PostResponse
import com.example.campusrootsinternapp.util.observeChange
import com.example.campusrootsinternapp.viewModel.Fragment2ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Fragment2 : BaseFragment() {

    private var _binding: Fragment2Binding? = null
    private val binding get() = _binding!!

    private val viewModel: Fragment2ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment2Binding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postId = arguments?.getString("text")

        binding.getPostsbutton.setOnClickListener {
            val postId = binding.editText.text.toString()
            if (postId.isDigitsOnly() != true) {
                showToast("Please enter a valid post ID")
            } else {
                displayPosts(postId)
            }
        }

        binding.backButtonFragment2.setOnClickListener {
            mFragmentNavigation.popFragment()
        }
    }

    private fun displayPosts(postId: String?) {
        viewModel.getPost(postId?.toInt() ?: 0)

        viewModel.postResponse.observeChange(viewLifecycleOwner) { postResponse ->
            binding.textView.text = buildString {
                append("User ID: ${postResponse.userId}\n")
                append("ID: ${postResponse.id}\n")
                append("Title: ${postResponse.title}\n")
                append("Body: ${postResponse.body}")
            }
        }

        viewModel.errorObserver.observeChange(viewLifecycleOwner) { error ->
            showToast(error)
        }

        viewModel.showLoader.observeChange(viewLifecycleOwner) { showLoader ->
            if (showLoader) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}