package com.example.leaarn_kotlin_for_android.Details

import android.graphics.Color

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.leaarn_kotlin_for_android.Adapter.SmallImagesAdapter
import com.example.leaarn_kotlin_for_android.R
import com.example.leaarn_kotlin_for_android.databinding.DetailsLayoutBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class DetailsFragment : Fragment() {
    private lateinit var binding: DetailsLayoutBinding
    private lateinit var smallImagesAdapter: SmallImagesAdapter
    private var imageList = mutableListOf<String>()
    private var isCheck: Boolean = true
    private lateinit var productId: String
    private var selectedSize: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DetailsLayoutBinding.inflate(layoutInflater, container, false)


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Product Details"

        binding.sizeL.setOnClickListener {
            selectedSize(binding.sizeL)
        }
        binding.sizeM.setOnClickListener {
            selectedSize(binding.sizeM)
        }
        binding.sizeS.setOnClickListener {
            selectedSize(binding.sizeS)
        }
        binding.sizeXL.setOnClickListener {
            selectedSize(binding.sizeXL)
        }

        if (isCheck){
            binding.checkCircleIcon.visibility = View.GONE
        }else{
            binding.checkCircleIcon.visibility = View.VISIBLE
        }
        binding.orderBtn.setOnClickListener {

            val firebase = FirebaseAuth.getInstance()
            val uid = firebase.uid
            val database = FirebaseDatabase.getInstance()
            val productRef = database.getReference("favourite").child(uid.toString()).child(productId)


            if (selectedSize?.isEmpty()!!){
                Toast.makeText(activity,"Please select Size", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            Toast.makeText(activity, "Order Placed", Toast.LENGTH_SHORT).show()
            if (isCheck){
                val hashMap:HashMap<String,String> = HashMap()
                hashMap.set("productId",productId)
                hashMap.set("Size", selectedSize!!)
                productRef.setValue(hashMap).addOnSuccessListener {
                    Toast.makeText(activity,"Product Added to Favourite",Toast.LENGTH_LONG).show()

                    val snackbar = Snackbar.make(binding.orderBtn,"Product Added to Favourite",Snackbar.LENGTH_INDEFINITE)
                        snackbar.setAction("Dismiss"){
                            snackbar.dismiss()
                        }
                        snackbar.show()

                }.addOnFailureListener {it
                    val snackbar = Snackbar.make(binding.orderBtn,"Product Failed to add ${it.message}",Snackbar.LENGTH_INDEFINITE)
                    snackbar.setAction("Dismiss"){
                        snackbar.dismiss()
                    }
                    snackbar.show()
                }
                binding.orderBtn.text = "Remove From Favourite"
                isCheck = false
                binding.checkCircleIcon.visibility = View.VISIBLE
            }else{
                binding.orderBtn.text = "Add to Favorite"
                productRef.removeValue()
                isCheck = true
                binding.checkCircleIcon.visibility = View.GONE
            }







        }
        getProductDetails()
    }
    private fun selectedSize(button: Button) {

        defaultBtnProperties()
        button.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.red))
        button.setTextColor(Color.WHITE)
        val buttonText: String = button.text.toString()
        selectedSize = buttonText
    }


    private fun getProductDetails() {
        productId = arguments?.getString("productId").toString()
        val database = FirebaseDatabase.getInstance()
        val productRef = database.getReference("products").child("men").child(productId.toString())
        productRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(productSnapshot: DataSnapshot) {
                imageList.clear()
                val productName = productSnapshot.child("productName").getValue(String::class.java)
                val description = productSnapshot.child("description").getValue(String::class.java)
                val price = productSnapshot.child("price").getValue(Double::class.java)
                val rating = productSnapshot.child("rating").getValue(Double::class.java)
                val storeName = productSnapshot.child("storeName").getValue(String::class.java)

                // Retrieve the first image URL from productImage1
                val productImageSnapshot = productSnapshot.child("productImage")
                for (imageSnapshot in productImageSnapshot.children) {
                    val imageUrl = imageSnapshot.getValue(String::class.java)
                    imageUrl?.let {
                        imageList.add(it)
                    }
                }

                Glide.with(requireActivity())
                    .load(imageList[0])
                    .into(binding.descriptionMainImgVP)

                smallImagesAdapter =
                    SmallImagesAdapter(imageList, object : SmallImagesAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int, url: String) {
                            Glide.with(requireActivity())
                                .load(url)
                                .into(binding.descriptionMainImgVP)
                        }

                    })
                binding.descriptionSmallImagesRecyclerView.adapter = smallImagesAdapter
                binding.descriptionSmallImagesRecyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                binding.descriptionProductName.text = productName
                binding.descriptionStoreName.text = "Featured by $storeName"
                binding.descriptionText.text = description
                binding.descriptionPriceTxt.text = price.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("MyApp", "Error ${error.message}")
            }
        })
    }

    private fun defaultBtnProperties() {
        binding.sizeL.setBackgroundTintList(
            ContextCompat.getColorStateList(
                requireContext(),
                R.color.lightGrey
            )
        )
        binding.sizeM.setBackgroundTintList(
            ContextCompat.getColorStateList(
                requireContext(),
                R.color.lightGrey
            )
        )
        binding.sizeS.setBackgroundTintList(
            ContextCompat.getColorStateList(
                requireContext(),
                R.color.lightGrey
            )
        )
        binding.sizeXL.setBackgroundTintList(
            ContextCompat.getColorStateList(
                requireContext(),
                R.color.lightGrey
            )
        )

        binding.sizeL.setTextColor(Color.BLACK)
        binding.sizeM.setTextColor(Color.BLACK)
        binding.sizeS.setTextColor(Color.BLACK)
        binding.sizeXL.setTextColor(Color.BLACK)
    }
}