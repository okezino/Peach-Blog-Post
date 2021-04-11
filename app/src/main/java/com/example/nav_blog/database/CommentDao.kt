package com.example.nav_blog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nav_blog.model.CommentData


@Dao
interface CommentDao {
    /**
     * This is the function to insert single new Comment Into the CommentDatabase Entity
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertComment(comment : CommentData)

    /**
     * This is the function to insert all Comments From Api  Into the CommentDatabase Entity
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBulkComment(comments : List<CommentData>)

    /**
     * This is the function to observe CommentDatabase Entity and get all info
     */

    @Query("SELECT * FROM CommentData")
    fun getAllComments() : LiveData<List<CommentData>>

}