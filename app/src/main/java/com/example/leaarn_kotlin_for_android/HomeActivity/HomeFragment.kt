package com.example.leaarn_kotlin_for_android.HomeActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leaarn_kotlin_for_android.Adapter.DiscountOfferPagerAdapter
import com.example.leaarn_kotlin_for_android.Models.DisOfferModel
import com.example.leaarn_kotlin_for_android.R
import com.example.leaarn_kotlin_for_android.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val disOfferModelsList = listOf(
            DisOfferModel(
                R.drawable.hoodie1,
                "10%"
            ),
            DisOfferModel(
                R.drawable.hoodie1,
                "20%"
            ),
            DisOfferModel(
                R.drawable.hoodie1,
                "30%"
            ),
            // Add more items as needed
        )
        val adapter = DiscountOfferPagerAdapter(disOfferModelsList)
        binding.offerViewPager.adapter = adapter

        binding.dotsIndicator.attachTo(binding.offerViewPager)

    }
}