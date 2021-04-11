package com.example.nav_blog.model

import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * class of UserData Use for the UserDataBase Entity
 */
@Entity(tableName = "UsersData")
data class UsersData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)
