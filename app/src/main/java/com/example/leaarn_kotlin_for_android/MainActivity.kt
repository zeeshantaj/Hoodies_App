package com.example.leaarn_kotlin_for_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.Animation.FragmentUtils
import com.example.leaarn_kotlin_for_android.Fragments.LoginFragment
import com.example.leaarn_kotlin_for_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FragmentUtils.changeFragment(supportFragmentManager,binding.mainFragmentContainer.id,LoginFragment())
        changeFragment(LoginFragment())
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.mainFragmentContainer.id, fragment)
        transaction.commit()
    }
}