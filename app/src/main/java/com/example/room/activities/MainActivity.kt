package com.example.room.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.room.R
import com.example.room.UserViewModel
import com.example.room.databinding.ActivityMainBinding
import com.example.room.user
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private lateinit var vm: UserViewModel
    private val TAG = "TAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProviders.of(this).get(UserViewModel::class.java)


        supportActionBar?.hide()
        /* binding..findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener{
             findNavController().navigate(R.id.action_listFragment_to_addFragment)*/

binding.btadd.setOnClickListener {

    insertDataToDatabase()

    /*vm.readAllData.observe(this, Observer { items ->
        if (items.isEmpty()) {
            vm.addUser(user(binding.etId?.text.toString().toInt(), binding.etName?.text.toString(),binding.etEmail?.text.toString(),binding.etMobile?.text.toString()))

        }

        *//*     Log.d(TAG, "ITEMS: $items")*//*
    })*/
}


    }
    private fun insertDataToDatabase(){

        val name = binding.etname.text.toString()
        val email = binding.etEmail.text.toString()
        val mobileno = binding.etMobNo.text.toString()


        if(inputCheck(name, email,mobileno)){
//            Create User object
            val user = user(0,name,email,mobileno)
//            Add data to Database
            vm.addUser(user)
            Toast.makeText(this,"Successfully added",Toast.LENGTH_LONG).show()
            val intent = Intent(this, userlistActivity::class.java)
            startActivity(intent)
        }else
            Toast.makeText(this,"Please fill out all fields..",Toast.LENGTH_LONG).show()


    }

    private fun inputCheck(name1: String,email1: String ,mobileno1: String): Boolean{
        return !(TextUtils.isEmpty(name1) && TextUtils.isEmpty(email1)&& TextUtils.isEmpty(mobileno1) )
    }
}