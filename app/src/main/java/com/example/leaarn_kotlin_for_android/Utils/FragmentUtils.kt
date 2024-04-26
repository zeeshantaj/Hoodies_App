package com.example.leaarn_kotlin_for_android.Utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentUtils {
    companion object{
        fun loadFragment(fragmentManager: FragmentManager, id: Int, fragment: Fragment) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(id, fragment)
            // go back on fragment one by one
            transaction.addToBackStack(null)
            transaction.commit()

        }
    }
}