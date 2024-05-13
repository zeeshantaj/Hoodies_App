package com.example.leaarn_kotlin_for_android.Favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.Models.ProductModel
import com.example.leaarn_kotlin_for_android.databinding.FragmentFavouriteBinding
import com.google.firebase.database.FirebaseDatabase

class FragmentFavourite: Fragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private var productList = mutableListOf<ProductModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }
    private fun getFavItems(){
        val database = FirebaseDatabase.getInstance()
        val productRef = database.getReference("products").child("men")
    }
}