package com.example.leaarn_kotlin_for_android.HomeActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaarn_kotlin_for_android.Adapter.CategoryRVAdapter
import com.example.leaarn_kotlin_for_android.Adapter.DiscountOfferPagerAdapter
import com.example.leaarn_kotlin_for_android.Adapter.ProductRVAdapter
import com.example.leaarn_kotlin_for_android.Models.DisOfferModel
import com.example.leaarn_kotlin_for_android.Models.ProductModel
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


        getDiscountOffer()
        getProducts()
        getCategory()
    }

    private fun getProducts() {
        val productList = listOf(
            ProductModel(
                "Hoodie", "Taj OutFit", 4.2f, 88.99, R.drawable.hoodie1
            ),
            ProductModel(
                "SweetShirt", "Mega Store", 4.1f, 30.99, R.drawable.hoodie1
            ),
            ProductModel(
                "Quote shirt", "G&P", 3.2f, 45.32, R.drawable.hoodie1
            ),
            ProductModel(
                "Track suit", "Dragon wear", 2.2f, 72.99, R.drawable.hoodie1
            ),
            ProductModel(
                "Printed T-shirt", "win win wear", 3.1f, 21.89, R.drawable.hoodie1
            ),
            // Add more items as needed
        )
        val adapter = ProductRVAdapter(productList)
        val LM = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding.productRV.layoutManager = LM
        binding.productRV.adapter = adapter

    }

    private fun getCategory() {
        val productList = listOf(
                "Hoodie","Sweetshirt",
                "yoga paints",
                "Track Suit",
                "T-shirt",
                "Denim",
                "Shorts",
                "trouser"
            // Add more items as needed
        )
        val adapter = CategoryRVAdapter(productList)
        val LM = LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)
        binding.productRV.layoutManager = LM
        binding.productRV.adapter = adapter
    }

    private fun getDiscountOffer() {
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