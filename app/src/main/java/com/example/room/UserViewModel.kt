package com.example.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//ViewModel provide data to the UI and survive configuration changes
class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<user>>
    private val repository: UserRepository

    //    init block is executed FIRST when ViewModel is created
    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
//    initializing repository and passing userDao
        repository = UserRepository(userDao)
//    get all data from user repository in the variable readAllData
        readAllData = repository.readAllData
    }

    fun searchDatabase(searchQuery: String): LiveData<List<user>> {
        return repository.searchDatabase(searchQuery)
    }

    //    function to add user to the repository
    fun addUser(user: user){
//    viewModelScope is part of coroutines
//    Dispatchers.IO will make sure the function will be running in background thread
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    //    function to update user to the repository
    fun updateUser(user: user){
//    viewModelScope is part of coroutines
//    Dispatchers.IO will make sure the function will be running in background thread
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(user)
        }
    }

    //    function to delete user
    fun deleteUser(user: user){
//    viewModelScope is part of coroutines
//    Dispatchers.IO will make sure the function will be running in background thread
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(user)
        }
    }

    //    function to delete all users
    fun deleteAllUsers(user: user){
//    viewModelScope is part of coroutines
//    Dispatchers.IO will make sure the function will be running in background thread
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsers(user)
        }
    }


}