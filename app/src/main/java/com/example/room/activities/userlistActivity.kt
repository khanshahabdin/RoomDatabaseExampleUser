package com.example.room.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast


import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.UserDatabase
import com.example.room.UserViewModel
import com.example.room.databinding.ActivityMainBinding
import com.example.room.databinding.ActivityUserlistBinding
import com.example.room.user
import com.google.android.material.floatingactionbutton.FloatingActionButton

class userlistActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private  lateinit var binding: ActivityUserlistBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var searchView: SearchView
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityUserlistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        val fab = findViewById<FloatingActionButton>(R.id.fabAdd)
        fab.setOnClickListener {
            // We are showing only toast message. However, you can do anything you need.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
         adapter = ListAdapter(this){user->
            mUserViewModel.deleteUser(user)


        }
        val recyclerView = binding.recyclerView1
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

       // userviewmodel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(this, Observer {
            adapter.setData(it)
        })


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {


        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
       /* Toast.makeText(this, searchQuery, Toast.LENGTH_SHORT).show()*/
        mUserViewModel.searchDatabase(searchQuery).observe(this, { user ->
            user.let {
                adapter.setData(it)
            }
        })
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.appSearchBar)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }*/

   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu!!.findItem(R.id.appSearchBar)
        searchView = searchItem.actionView as SearchView
        searchView.setSubmitButtonEnabled(true)
        searchView.setQueryHint("Search either - MindOrks, GetMeAnApp, BestContentApp, Hackerspace")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                getNamesFromDb(newText)
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                getNamesFromDb(query)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }*/

   /* private fun getNamesFromDb(searchText: String) {
        val searchTextQuery = "%$searchText%"
        UserDatabase.getDatabase(application).userDao().getChapterName(searchTextQuery)
            .observe(this, object : Observer<List<user>> {
                override fun onChanged(user: List<user>?) {
                    if (user == null) {
                        return
                    }
                    val adapter = ListAdapter(this@userlistActivity){user->
                        mUserViewModel.deleteUser(user)
                    }
                    val recyclerView = binding.recyclerView1
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this@userlistActivity)

                    // userviewmodel
                    mUserViewModel = ViewModelProvider(this@userlistActivity).get(UserViewModel::class.java)
                    mUserViewModel.readAllData.observe(this@userlistActivity, Observer {
                        adapter.setData(user)
                    })
                }
            })
    }*/

}