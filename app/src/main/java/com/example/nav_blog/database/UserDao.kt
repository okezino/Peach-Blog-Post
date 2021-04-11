package com.example.nav_blog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nav_blog.model.CommentData
import com.example.nav_blog.model.UsersData

@Dao
interface UserDao {

    /**
     * This is the function to insert single new Users Into the UsersDatabase Entity
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user : UsersData)

    /**
     * This is the function to insert all Users From Api  Into the UsersDatabase Entity
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBulkUsers(users : List<UsersData>)

    /**
     * This is the function to observe UsersDatabase Entity and get all info
     */

    @Query("SELECT * FROM UsersData")
    fun getAllUsers() : LiveData<List<UsersData>>
}