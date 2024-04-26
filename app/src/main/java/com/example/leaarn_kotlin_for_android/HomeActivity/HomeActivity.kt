package com.example.leaarn_kotlin_for_android.HomeActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.Chat.FragmentChat
import com.example.leaarn_kotlin_for_android.Favourite.FragmentFavourite
import com.example.leaarn_kotlin_for_android.R
import com.example.leaarn_kotlin_for_android.Settings.FragmentSettings
import com.example.leaarn_kotlin_for_android.Utils.FragmentUtils
import com.example.leaarn_kotlin_for_android.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var currentFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar()

        currentFragment = HomeFragment()
        FragmentUtils.loadFragment(supportFragmentManager,binding.mainFragmentContainer.id,currentFragment!!)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.navHome -> {
                    if (currentFragment !is HomeFragment) {
                        currentFragment = HomeFragment()
                        FragmentUtils.loadFragment(supportFragmentManager,binding.mainFragmentContainer.id,currentFragment!!)
                        //binding.toolbar.setTitle("")
                        supportActionBar?.title = "Products"
                    }
                }
                R.id.navFav -> {
                    if (currentFragment !is FragmentFavourite) {
                        currentFragment = FragmentFavourite()
                        FragmentUtils.loadFragment(supportFragmentManager,binding.mainFragmentContainer.id,currentFragment!!)
                        //binding.toolbar.setTitle("")
                        supportActionBar?.title = "Favourite"
                    }
                }
                R.id.navChat -> {
                    if (currentFragment !is FragmentChat) {
                        currentFragment = FragmentChat()
                        FragmentUtils.loadFragment(supportFragmentManager,binding.mainFragmentContainer.id,currentFragment!!)
                        //binding.toolbar.setTitle("Chat")
                        supportActionBar?.title = "Chat"
                    }
                }
                R.id.navSettings -> {
                    if (currentFragment !is FragmentSettings) {
                        currentFragment = FragmentSettings()
                        FragmentUtils.loadFragment(supportFragmentManager,binding.mainFragmentContainer.id,currentFragment!!)
                        //binding.toolbar.setTitle("Settings")
                        supportActionBar?.title = "Settings"
                    }
                }
            }
            true
        }

    }
    private fun setActionBar(){
        setSupportActionBar(binding.toolbar)
    }
}