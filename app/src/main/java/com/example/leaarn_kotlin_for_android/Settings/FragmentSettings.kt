package com.example.leaarn_kotlin_for_android.Settings

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.leaarn_kotlin_for_android.MainActivity
import com.example.leaarn_kotlin_for_android.databinding.FragmentFavouriteBinding
import com.example.leaarn_kotlin_for_android.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth

class FragmentSettings: Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentSettingsBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.logoutCard.setOnClickListener {

            val builder = AlertDialog.Builder(activity)

            builder.setTitle("Account Logout")
            builder.setMessage("Are you sure you want to logout from account?")
            builder.setCancelable(false)
            builder.setPositiveButton("Yes",){dialog, which ->

                //auth.signOut()
                FirebaseAuth.getInstance().signOut()
                dialog.dismiss()
                startActivity(Intent(activity,MainActivity::class.java))

                activity?.finish()


            }
            builder.setNegativeButton("No"){dialog, which ->
                dialog.dismiss()
            }
            val alertDialogue = builder.create()
            alertDialogue.show()



        }
        binding.deleteCard.setOnClickListener {

        }
    }
}