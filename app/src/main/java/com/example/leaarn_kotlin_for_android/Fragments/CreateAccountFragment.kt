package com.example.leaarn_kotlin_for_android.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.leaarn_kotlin_for_android.HomeActivity.HomeActivity
import com.example.leaarn_kotlin_for_android.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth

class CreateAccountFragment : Fragment() {


    private lateinit var binding: FragmentCreateAccountBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateAccountBinding.inflate(layoutInflater, container, false)
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.signupBtn.setOnClickListener {
            val name = binding.nameEd.text
            val email = binding.emailEd.text
            val pass = binding.passwordEd.text
            val conPass = binding.conPasswordEd.text

            if (name?.isEmpty()!!) {
                binding.nameEd.error = "Name is empty"
                return@setOnClickListener
            }
            if (email?.isEmpty()!!) {
                binding.emailEd.error = "email is empty"
                return@setOnClickListener
            }
            if (pass?.isEmpty()!!) {
                binding.passwordEd.error = "password is empty"
                return@setOnClickListener
            }
            if (conPass?.isEmpty()!!) {
                binding.conPasswordEd.error = "confirm password is empty"
                return@setOnClickListener
            }
            if (pass != conPass){
                Toast.makeText(activity,"Password and confirm password should be same", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

             auth.createUserWithEmailAndPassword(email.toString(),pass.toString()).addOnSuccessListener {
                 val intent = Intent(activity, HomeActivity::class.java)
                 startActivity(intent)
             }

        }
     }

}