package com.example.nav_blog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nav_blog.model.PostData

@Dao
interface PostDao {

    /**
     * This is the function to insert single new Post Into the PostDatabase Entity
     */
     @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post : PostData)

    /**
     * This is the function to insert all Post From Api  Into the PostDatabase Entity
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBulkPost(posts:List<PostData>)

    /**
     * This is the function to observe PostDatabase Entity and get all info
     */

    @Query("SELECT * FROM PostData ORDER BY id DESC")
    fun readAllPostData() : LiveData<List<PostData>>

}