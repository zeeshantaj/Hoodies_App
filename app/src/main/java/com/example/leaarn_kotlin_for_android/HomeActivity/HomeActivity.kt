package com.example.leaarn_kotlin_for_android.HomeActivity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.R
import com.example.leaarn_kotlin_for_android.databinding.ActivityHomeBinding
import com.example.leaarn_kotlin_for_android.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.navHome -> loadFragment(HomeFragment())
                R.id.navFav -> loadFragment(HomeFragment())
                R.id.navChat -> loadFragment(HomeFragment())
                R.id.navSettings -> loadFragment(HomeFragment())
            }
            true
        }


    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.mainFragmentContainer.id, fragment)
        transaction.commit()
    }
}