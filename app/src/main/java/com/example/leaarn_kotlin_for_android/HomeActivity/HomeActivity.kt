package com.example.leaarn_kotlin_for_android.HomeActivity

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.leaarn_kotlin_for_android.Chat.FragmentChat
import com.example.leaarn_kotlin_for_android.Favourite.FragmentFavourite
import com.example.leaarn_kotlin_for_android.R
import com.example.leaarn_kotlin_for_android.Settings.FragmentSettings
import com.example.leaarn_kotlin_for_android.Utils.FragmentUtils
import com.example.leaarn_kotlin_for_android.databinding.ActivityHomeBinding
import com.example.leaarn_kotlin_for_android.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var currentFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentFragment = HomeFragment()
        FragmentUtils.loadFragment(supportFragmentManager,binding.mainFragmentContainer.id,currentFragment!!)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId){
//                R.id.navHome -> loadFragment(HomeFragment())
//                R.id.navFav -> loadFragment(FragmentFavourite())
//                R.id.navChat -> loadFragment(FragmentChat())
//                R.id.navSettings -> loadFragment(FragmentSettings())
                R.id.navHome -> {
                    if (currentFragment !is HomeFragment) {
                        currentFragment = HomeFragment()
                        FragmentUtils.loadFragment(supportFragmentManager,binding.mainFragmentContainer.id,currentFragment!!)
                        binding.toolbar.setTitle("Products")
                    }
                }
                R.id.navFav -> {
                    if (currentFragment !is FragmentFavourite) {
                        currentFragment = FragmentFavourite()
                        FragmentUtils.loadFragment(supportFragmentManager,binding.mainFragmentContainer.id,currentFragment!!)
                        binding.toolbar.setTitle("Favourite")
                    }
                }
                R.id.navChat -> {
                    if (currentFragment !is FragmentChat) {
                        currentFragment = FragmentChat()
                        FragmentUtils.loadFragment(supportFragmentManager,binding.mainFragmentContainer.id,currentFragment!!)
                        binding.toolbar.setTitle("Chat")
                    }
                }
                R.id.navSettings -> {
                    if (currentFragment !is FragmentSettings) {
                        currentFragment = FragmentSettings()
                        FragmentUtils.loadFragment(supportFragmentManager,binding.mainFragmentContainer.id,currentFragment!!)
                        binding.toolbar.setTitle("Settings")
                    }
                }
            }
            true
        }
    }
//    private fun loadFragment(fragmentManager: FragmentManager,id: Int,fragment: Fragment) {
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(id, fragment)
//        transaction.commit()
//    }
}