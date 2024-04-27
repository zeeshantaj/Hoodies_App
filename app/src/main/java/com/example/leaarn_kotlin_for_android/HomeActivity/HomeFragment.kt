package com.example.leaarn_kotlin_for_android.HomeActivity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaarn_kotlin_for_android.Adapter.CategoryRVAdapter
import com.example.leaarn_kotlin_for_android.Adapter.DiscountOfferPagerAdapter
import com.example.leaarn_kotlin_for_android.Adapter.ProductRVAdapter
import com.example.leaarn_kotlin_for_android.Interface.OnCategoryItemClicked
import com.example.leaarn_kotlin_for_android.Interface.OnProductItemClicked
import com.example.leaarn_kotlin_for_android.Models.DisOfferModel
import com.example.leaarn_kotlin_for_android.Models.ProductModel
import com.example.leaarn_kotlin_for_android.R
import com.example.leaarn_kotlin_for_android.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Timer
import java.util.TimerTask

class HomeFragment : Fragment(), OnCategoryItemClicked {

    private lateinit var binding: FragmentHomeBinding
    private var selectedCategory: String? = null
    private var productList = mutableListOf<ProductModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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


    private fun getCategory() {
        val productList = listOf(
            "Hoodie", "Sweetshirt",
            "yoga paints",
            "Track Suit",
            "T-shirt",
            "Denim",
            "Shorts",
            "trouser"
            // Add more items as needed
        )
        val adapter = CategoryRVAdapter(productList, this)
        val defaultCategory = productList.firstOrNull()
        val LM = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRV.layoutManager = LM
        binding.categoryRV.adapter = adapter

        // set default category before clicking any cat item
        defaultCategory?.let {
            selectedCategory = it
        }
    }

    private fun getProducts() {


        val database = FirebaseDatabase.getInstance()
        val productRef = database.getReference("products").child("men")
        productRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                for (productSnapshot in snapshot.children) {
//                    val product = childSnapshot.getValue(ProductModel::class.java)
//                    Log.d("MyApp", "product details ${product?.productName}")
//                    val productImg = childSnapshot.child("productImage")

                    val productId = productSnapshot.key
                    val productName = productSnapshot.child("productName").getValue(String::class.java)
                    val price = productSnapshot.child("price").getValue(Double::class.java)
                    val rating = productSnapshot.child("rating").getValue(Double::class.java)
                    val storeName = productSnapshot.child("storeName").getValue(String::class.java)

                    // Retrieve the first image URL from productImage1
                    val productImageSnapshot = productSnapshot.child("productImage")
                    var imageUrl: String? = null
                    for (imageSnapshot in productImageSnapshot.children) {
                        imageUrl = imageSnapshot.getValue(String::class.java)
                        break // Exit the loop after getting the first image URL
                    }
                    val model = ProductModel()
                    model.storeName = storeName
                    model.rating = rating!!
                    model.price = price!!
                    model.productName = productName
                    model.productImage = imageUrl
                    model.productId = productId

                    model.let {
                        productList.add(model)
                    }
                }
                val adapter = ProductRVAdapter(productList)
                val LM = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
                binding.productRV.layoutManager = LM
                binding.productRV.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("MyApp", "Error " + error.message);
            }
        })

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

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    val currentTime = binding.offerViewPager.currentItem
                    val nextItem = if (currentTime == adapter.itemCount - 1) 0 else currentTime + 1
                    binding.offerViewPager.setCurrentItem(nextItem, true)
                }
            }
        }, 5000, 5000)

    }

    override fun onCategoryClick(category: String?) {
        selectedCategory = category
        Log.d("MyApp", "category Name$category")
    }

}