package com.example.nav_blog.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model for Comment details use for the Comment Entity
 */
@Entity(tableName = "CommentData")
data class CommentData(
    val body: String,
    val email: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    val name: String,
    val postId: Int
)