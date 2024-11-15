package com.example.campusrootsinternapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.activityViewModels
import com.example.campusrootsinternapp.base.BaseFragment
import com.example.campusrootsinternapp.databinding.Fragment2Binding
import com.example.campusrootsinternapp.util.observeChange
import com.example.campusrootsinternapp.viewModel.Fragment2ViewModel

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

        binding.getPostsbutton.setOnClickListener { onGetPostsButtonClick() }
        binding.backButtonFragment2.setOnClickListener { onBackPressed() }
    }

    private fun onGetPostsButtonClick() {
        val postId = binding.editText.text.toString()
        if (!postId.isDigitsOnly()) {
            showToast("Please enter a valid post ID")
            return
        }
        displayPosts(postId.toInt())
    }

    private fun displayPosts(postId: Int) {
        viewModel.getPost(postId)
        observePostResponse()
        observeError()
        observeLoader()
    }

    private fun observePostResponse() {
        viewModel.postResponse.observeChange(viewLifecycleOwner) { postResponse ->
            binding.textView.text = buildString {
                append("User ID: ${postResponse.userId}\n")
                append("ID: ${postResponse.id}\n")
                append("Title: ${postResponse.title}\n")
                append("Body: ${postResponse.body}")
            }
        }
    }

    private fun observeError() {
        viewModel.errorObserver.observeChange(viewLifecycleOwner) { error ->
            showToast(error)
        }
    }

    private fun observeLoader() {
        viewModel.showLoader.observeChange(viewLifecycleOwner) { showLoader ->
            binding.progressBar.visibility = if (showLoader) View.VISIBLE else View.GONE
        }
    }

    private fun onBackPressed() {
        mFragmentNavigation.popFragment()
    }
}