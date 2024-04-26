package com.example.leaarn_kotlin_for_android.Payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.databinding.FragmentPaymentBinding

class FragmentPayment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding = FragmentPaymentBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onStart() {
        super.onStart()

       //  Access the activity's supportActionBar
        (activity as AppCompatActivity).supportActionBar?.title = "Payment"
    }
}