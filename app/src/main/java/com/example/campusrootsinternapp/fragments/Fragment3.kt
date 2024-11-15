package com.example.campusrootsinternapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campusrootsinternapp.adapter.PostAdapter
import com.example.campusrootsinternapp.base.BaseFragment
import com.example.campusrootsinternapp.databinding.Fragment3Binding
import com.example.campusrootsinternapp.model.PostResponse
import com.example.campusrootsinternapp.viewModel.Fragment3ViewModel

/**
 * A simple [Fragment] subclass.
 */
class Fragment3 : BaseFragment() {

    private var _binding: Fragment3Binding? = null
    private val binding get() = _binding!!

    private val viewModel: Fragment3ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment3Binding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        setupListeners()
    }

    private fun setupObserver() {
        viewModel.postResponse.observe(viewLifecycleOwner, ::handlePostResponse)
        viewModel.showLoader.observe(viewLifecycleOwner, ::handleShowLoader)
    }

    private fun handlePostResponse(postResponse: List<PostResponse>) {
        println(postResponse)
        val adapter = PostAdapter(postResponse)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun handleShowLoader(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setupListeners() {
        binding.backButtonFragment3.setOnClickListener {
            mFragmentNavigation.popFragment()
        }
    }
}