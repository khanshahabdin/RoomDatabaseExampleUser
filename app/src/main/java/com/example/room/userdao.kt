package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface userDao {
    //    Function to add a new user to the database, here onConflict is called when there
//    will be an existing user with same parameters then we've set it to ignore
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: user)
    /*@Query("SELECT * FROM user_table1  WHERE name LIKE :query")
    fun getUserName(query: String): Flow<List<user>>*/


    @Query("SELECT * FROM user_table1 WHERE name LIKE :searchQuery OR id LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<user>>


    //    Query to return all the user, the list will be wrapped in the LiveData
    @Query("SELECT * FROM user_table1 ORDER BY id ASC")
    fun readAllData(): LiveData<List<user>>

    //    Function to update the existing user
    @Update
    suspend fun updateUser(user: user)

    //    Function to delete a single user
    @Delete
    suspend fun deleteUser(user: user)

    //    Function to delete all of the existing user
    @Delete
    suspend fun deleteAllUsers(user: user)
}