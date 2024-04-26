package com.example.leaarn_kotlin_for_android.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.Animation.FragmentUtils
import com.example.leaarn_kotlin_for_android.HomeActivity.HomeActivity
import com.example.leaarn_kotlin_for_android.R
import com.example.leaarn_kotlin_for_android.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentLoginBinding.inflate(layoutInflater, container, false)
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.loginBtn.setOnClickListener {
            val name = binding.emailEd.text
            val pass = binding.passwordEd.text
            if (name?.isEmpty()!!){
                binding.emailEd.error = "Name is empty"
                setAnimation(binding.emailEd)
                return@setOnClickListener
            }
            if (pass?.isEmpty()!!){
                binding.passwordEd.error = "Name is empty"
                setAnimation(binding.passwordEd)
                return@setOnClickListener
            }
            if (!binding.checkBox.isChecked){
                setAnimation(binding.checkBox)
                Toast.makeText(activity,"please check the ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(name.toString(),pass.toString()).addOnSuccessListener {
                val intent = Intent(activity, HomeActivity::class.java)
                startActivity(intent)
            }

        }
        binding.createAccTxt.setOnClickListener {
            activity?.let { it1 -> FragmentUtils.changeFragment(it1.supportFragmentManager,R.id.loginFragmentContainer,CreateAccountFragment()) }
        }


    }
    private fun setAnimation(view: View) {
        val shake = android.view.animation.AnimationUtils.loadAnimation(activity, R.anim.shake_animation)
        view.startAnimation(shake)
    }
}