package com.example.leaarn_kotlin_for_android.Settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.databinding.FragmentFavouriteBinding
import com.example.leaarn_kotlin_for_android.databinding.FragmentSettingsBinding

class FragmentSettings: Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }
}