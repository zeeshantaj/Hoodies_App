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
import com.example.leaarn_kotlin_for_android.Adapter.SmallImagesAdapter
import com.example.leaarn_kotlin_for_android.Payment.FragmentPayment
import com.example.leaarn_kotlin_for_android.R
import com.example.leaarn_kotlin_for_android.Utils.FragmentUtils
import com.example.leaarn_kotlin_for_android.databinding.DetailsLayoutBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class DetailsFragment : Fragment(),SmallImagesAdapter.OnItemClickListener{
    private lateinit var binding : DetailsLayoutBinding
    private lateinit var smallImagesAdapter: SmallImagesAdapter
    private lateinit var smallImages: List<Int>
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


//        smallImages = listOf(
//            R.drawable.hoodie1,
//            R.drawable.google_pay_logo,
//            R.drawable.hoodie1
//        )


        binding.orderBtn.setOnClickListener {
            Toast.makeText(activity, "Order Placed", Toast.LENGTH_SHORT).show()
            activity?.let {
                FragmentUtils.loadFragment(it.supportFragmentManager ,R.id.mainFragmentContainer,FragmentPayment())
            }

        }
        getProductDetails()

    }
    private fun getProductDetails(){
        val productId = arguments?.getString("productId")
        val database = FirebaseDatabase.getInstance()
        val productRef = database.getReference("products").child("men").child(productId.toString())
        productRef.addValueEventListener(object : ValueEventListener,
            SmallImagesAdapter.OnItemClickListener {
            override fun onDataChange(productSnapshot: DataSnapshot) {
                val productName = productSnapshot.child("productName").getValue(String::class.java)
                val description = productSnapshot.child("description").getValue(String::class.java)
                val price = productSnapshot.child("price").getValue(Double::class.java)
                val rating = productSnapshot.child("rating").getValue(Double::class.java)
                val storeName = productSnapshot.child("storeName").getValue(String::class.java)

                // Retrieve the first image URL from productImage1
                val productImageSnapshot = productSnapshot.child("productImage")
                var imageList = mutableListOf<String>()
                for (imageSnapshot in productImageSnapshot.children) {
                    val imageUrl = imageSnapshot.getValue(String::class.java)
                    imageUrl?.let {
                        imageList.add(it)

                    }

                }
                smallImagesAdapter = SmallImagesAdapter(imageList,this)

                binding.descriptionMainImgVP.setImageResource(smallImages[0])

                binding.descriptionSmallImagesRecyclerView.adapter = smallImagesAdapter
                binding.descriptionSmallImagesRecyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                Log.d("MyApp", "name $productName")
                Log.d("MyApp", "price $price")
                Log.d("MyApp", "rating $rating")
                Log.d("MyApp", "store $storeName")
//                Log.d("MyApp", "image url $imageUrl")
                binding.descriptionProductName.text = productName
                binding.descriptionStoreName.text = "Featured by $storeName"
                binding.descriptionText.text = description
                binding.descriptionPriceTxt.text = price.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("MyApp", "Error ${error.message}")

            }

            override fun onItemClick(position: Int) {
                binding.descriptionMainImgVP.setImageResource(smallImages[position])
            }
        })
    }
    override fun onItemClick(position: Int) {

        //Toast.makeText(activity, "img clicked$position", Toast.LENGTH_SHORT).show()
    }
    private fun selectedSize(button:Button){

        defaultBtnProperties()
        button.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.red))
        button.setTextColor(Color.WHITE)
        val buttonText: String = button.text.toString()
        Toast.makeText(activity, "Clicked Button: $buttonText", Toast.LENGTH_SHORT).show()

    }
    private fun defaultBtnProperties(){
        binding.sizeL.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.lightGrey))
        binding.sizeM.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.lightGrey))
        binding.sizeS.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.lightGrey))
        binding.sizeXL.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.lightGrey))

        binding.sizeL.setTextColor(Color.BLACK)
        binding.sizeM.setTextColor(Color.BLACK)
        binding.sizeS.setTextColor(Color.BLACK)
        binding.sizeXL.setTextColor(Color.BLACK)
    }



}