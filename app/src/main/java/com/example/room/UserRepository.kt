package com.example.room

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

/*
* A repository class abstracts access to multiple data sources
 */

class UserRepository (private val userDao: userDao){

    //    variable to readAllData that we'll be getting from userDao
    val readAllData: LiveData<List<user>> = userDao.readAllData()


    fun searchDatabase(searchQuery: String): LiveData<List<user>> {
        return userDao.searchDatabase(searchQuery)
    }



    //    a suspend function to add new User to our database which will
//    call UserDao and add new User
    suspend fun addUser(user: user){
        userDao.addUser(user)
    }

    //    a suspend function to update the existing user in DB
    suspend fun updateUser(user: user){
        userDao.updateUser(user)
    }

    //    a suspend function to delete a user from DB
    suspend fun deleteUser(user: user){
        userDao.deleteUser(user)
    }

    //    a suspend function to delete all user from DB
    suspend fun deleteAllUsers(user: user){
        userDao.deleteAllUsers(user)
    }


}