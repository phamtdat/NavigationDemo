package com.example.navigationdemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationdemo.MainActivity
import com.example.navigationdemo.R

class AppFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_app, container, false)
        view.findViewById<TextView>(R.id.tv_to_third_level)?.setOnClickListener {
            this@AppFragment.findNavController().navigate(AppFragmentDirections.toThirdLevel())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavigation(true)
    }
}