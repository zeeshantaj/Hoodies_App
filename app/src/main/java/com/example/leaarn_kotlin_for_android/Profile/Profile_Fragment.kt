package com.example.leaarn_kotlin_for_android.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.databinding.FragmentProfileBinding

class Profile_Fragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}