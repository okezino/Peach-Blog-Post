package com.example.nav_blog.util

import com.example.nav_blog.model.PostData
import com.example.nav_blog.model.UsersData

/**
 * Function to get writer's name and company name using the userId from the Post
 */

object getUserDetails {

    fun getUserName(post : PostData, usersInfo : MutableList<UsersData>) : String{

        var name = "Joseph Okezi"

        for(i in usersInfo){
            if(post.userId == i.id) name = i.name
        }
        return name
    }
}