package com.example.leaarn_kotlin_for_android.Animation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentUtils {
    companion object{
        fun changeFragment(fragmentManager: FragmentManager,id: Int,fragment: Fragment) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(id, fragment)
            transaction.commit()
        }
    }
}