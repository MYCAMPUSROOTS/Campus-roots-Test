package com.example.campusrootsinternapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.campusrootsinternapp.base.BaseFragment
import com.example.campusrootsinternapp.databinding.FragmentPostsBinding

class FragmentPosts : BaseFragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val text = binding.editTextText.text.toString()
            val fragment = Fragment2()
            val args = Bundle()
            args.putString("text", text)
            fragment.arguments = args
            mFragmentNavigation.pushFragment(fragment)
        }
    }
}