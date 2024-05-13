package com.example.leaarn_kotlin_for_android.Favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.Models.ProductModel
import com.example.leaarn_kotlin_for_android.databinding.FragmentFavouriteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FragmentFavourite: Fragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private var favItemList = mutableListOf<ProductModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        getFavItems()
        super.onStart()
    }
    private fun getFavItems(){
        val firebase = FirebaseAuth.getInstance()
        val uid = firebase.uid
        val database = FirebaseDatabase.getInstance()
        val productRef = database.getReference("favourite").child(uid.toString())
        productRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                favItemList.clear()
                if (snapshot.exists()){
                    for (favItemSnapshot in snapshot.children){
                        // get fav items
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("MyApp","Error ${error.message}")
            }

        })

    }
}