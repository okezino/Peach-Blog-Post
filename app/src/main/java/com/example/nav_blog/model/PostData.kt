package com.example.nav_blog.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * class of PostData Use for the PostDataBase Entity
 */
@Entity(tableName = "PostData")
data class PostData(
        val body: String,
        @PrimaryKey(autoGenerate = true)
        val id: Int?,
        val title: String,
        var userId: Int
)