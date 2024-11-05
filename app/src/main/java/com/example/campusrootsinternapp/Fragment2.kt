package com.example.campusrootsinternapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.campusrootsinternapp.base.BaseFragment
import com.example.campusrootsinternapp.model.PostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Fragment2 : BaseFragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        textView = view.findViewById(R.id.textView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Display loading indicator
        progressBar.visibility = View.VISIBLE

        // Make the API call
        RetrofitInstance.api.getPost().enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                // Hide loading indicator
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val post = response.body()
                    textView.text = buildString {
                        append("User ID: ${post?.userId}\n")
                        append("ID: ${post?.id}\n")
                        append("Title: ${post?.title}\n")
                        append("Body: ${post?.body}")
                    }
                } else {
                    textView.text = "Failed to retrieve data"
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                // Hide loading indicator
                progressBar.visibility = View.GONE
                textView.text = "Error: ${t.message}"
            }
        })
    }
}