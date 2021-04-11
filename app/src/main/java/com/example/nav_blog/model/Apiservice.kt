package com.example.nav_blog.model

import androidx.lifecycle.LiveData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface ApiService {
    /**
     * Get the list of 100 Post from Api Call
     */

    @GET("posts")
    suspend fun getPosts() : List<PostData>

    /**
     * Get list of Users from Api call
     */

    @GET("users")
    suspend fun getUsers() : List<UsersData>



    /**
     * route to get comments from Api Call
     */
    @GET("comments")
    suspend fun getComments(): List<CommentData>


}