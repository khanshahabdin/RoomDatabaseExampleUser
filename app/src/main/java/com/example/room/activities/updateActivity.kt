package com.example.room.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.example.room.UserViewModel
import com.example.room.databinding.ActivityMainBinding
import com.example.room.databinding.ActivityUpdateBinding
import com.example.room.databinding.ActivityUserlistBinding
import com.example.room.user

class updateActivity : AppCompatActivity() {


    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: ActivityUpdateBinding
    var id :Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        id =intent.getStringExtra("id").toString().toInt()
        val name=intent.getStringExtra("name")
        val email=intent.getStringExtra("email")
        val mob=intent.getStringExtra("mob")

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.etname.setText(name ?: "")
        binding.etEmail.setText(email ?: "")
        binding.etMobNo.setText(mob ?: "")

        binding.btnregister.setOnClickListener {

            updateItem()
        }
    }

    private fun updateItem(){
        val vName = binding.etname.text.toString()
        val vemail =  binding.etEmail.text.toString()
        val vmob =  binding.etMobNo.text.toString()

        if(inputCheck(vName,vemail,vmob)){
//            Create User Object
            val updatedUser =  user(id ?:0 ,vName,vemail,vmob)

//            Update User Object
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(this,"Updated successfully", Toast.LENGTH_LONG).show()

            val intent = Intent(this, userlistActivity::class.java)
            startActivity(intent)

        }else{
            Toast.makeText(this,"Please fill out all fields..", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(Name: String,email: String, mob: String): Boolean{
        return !(TextUtils.isEmpty(Name) && TextUtils.isEmpty(email) &&  TextUtils.isEmpty(mob))
    }



}


