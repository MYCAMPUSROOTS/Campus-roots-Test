package com.example.campusrootsinternapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campusrootsinternapp.adapter.Adapter
import com.example.campusrootsinternapp.adapter.PostAdapter
import com.example.campusrootsinternapp.base.BaseFragment
import com.example.campusrootsinternapp.databinding.Fragment3Binding
import com.example.campusrootsinternapp.model.PostResponse
import com.example.campusrootsinternapp.util.observeChange
import com.example.campusrootsinternapp.viewModel.Fragment3ViewModel
import com.google.gson.Gson

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

        viewModel.postResponse.observe(viewLifecycleOwner) { postResponse ->
            println(postResponse)
            val gson = Gson()
            val postResponseList: List<PostResponse> = gson.fromJson(gson.toJson(postResponse), Array<PostResponse>::class.java).toList()

            val adapter = PostAdapter(postResponseList)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.showLoader.observe(viewLifecycleOwner) { showLoader ->
            if (showLoader) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        binding.backButtonFragment3.setOnClickListener {
            mFragmentNavigation.popFragment()
        }
    }
}