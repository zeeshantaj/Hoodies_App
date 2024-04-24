package com.example.leaarn_kotlin_for_android.Details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.Interface.OnProductItemClicked
import com.example.leaarn_kotlin_for_android.databinding.DetailsLayoutBinding
import com.example.leaarn_kotlin_for_android.databinding.FragmentHomeBinding

class DetailsFragment : Fragment(),OnProductItemClicked{
    private lateinit var binding : DetailsLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DetailsLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onProductClick(productID: Int) {

    }

}