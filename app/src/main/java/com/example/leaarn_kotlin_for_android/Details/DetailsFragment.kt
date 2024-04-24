package com.example.leaarn_kotlin_for_android.Details

import android.R.attr
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.Interface.OnProductItemClicked
import com.example.leaarn_kotlin_for_android.R
import com.example.leaarn_kotlin_for_android.databinding.DetailsLayoutBinding


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

    override fun onStart() {
        super.onStart()


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
    }
    fun selectedSize(button:Button){

        defaultBtnProperties()
        button.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.red));
        button.setTextColor(Color.WHITE)
        val buttonText: String = button.text.toString()
        Toast.makeText(activity, "Clicked Button: $buttonText", Toast.LENGTH_SHORT).show()

    }
    private fun defaultBtnProperties(){
        binding.sizeL.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.lightGrey));
        binding.sizeM.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.lightGrey));
        binding.sizeS.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.lightGrey));
        binding.sizeXL.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.lightGrey));

        binding.sizeL.setTextColor(Color.BLACK)
        binding.sizeM.setTextColor(Color.BLACK)
        binding.sizeS.setTextColor(Color.BLACK)
        binding.sizeXL.setTextColor(Color.BLACK)
    }

    override fun onProductClick(productID: Int) {

    }

}