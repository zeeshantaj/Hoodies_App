package com.example.leaarn_kotlin_for_android.Fragnents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.Animation.FragmentUtils
import com.example.leaarn_kotlin_for_android.R
import com.example.leaarn_kotlin_for_android.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.loginBtn.setOnClickListener {
            val name = binding.nameEd.text
            if (name?.isEmpty()!!){
                Toast.makeText(activity,"please Enter the name", Toast.LENGTH_SHORT).show()
                setAnimation(binding.nameEd)
                return@setOnClickListener
            }
            if (!binding.checkBox.isChecked){
                setAnimation(binding.checkBox)
                Toast.makeText(activity,"please check the ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(activity,"\n"+binding.checkBox.isChecked,
                Toast.LENGTH_SHORT).show()
            activity?.let { it1 -> FragmentUtils.changeFragment(it1.supportFragmentManager,R.id.mainFragmentContainer,CreateAccountFragment()) }

        }
    }
    private fun setAnimation(view: View) {
        val shake = android.view.animation.AnimationUtils.loadAnimation(activity, R.anim.shake_animation)
        view.startAnimation(shake)
    }
}