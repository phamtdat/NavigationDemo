package com.example.navigationdemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationdemo.MainActivity
import com.example.navigationdemo.R

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        view.findViewById<Button>(R.id.btn_to_ads)?.setOnClickListener {
            this@SettingsFragment.findNavController().navigate(SettingsFragmentDirections.toAds())
        }
        view.findViewById<Button>(R.id.btn_to_app)?.setOnClickListener {
            this@SettingsFragment.findNavController().navigate(SettingsFragmentDirections.toApp())
        }
        view.findViewById<Button>(R.id.btn_to_home)?.setOnClickListener {
            this@SettingsFragment.findNavController().navigate(SettingsFragmentDirections.toHome())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavigation(false)
    }
}