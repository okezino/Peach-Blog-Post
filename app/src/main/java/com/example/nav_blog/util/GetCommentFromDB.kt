package com.example.nav_blog.util

import com.example.nav_blog.model.CommentData

/**
 * Get updated list from the comment database
 */
object getCommentsFromDB {

    fun getArray(arr : List<CommentData> , postId : Int) : List<CommentData>{
        var updatedComments = mutableListOf<CommentData>()
        for(i in arr){
            if(i.postId == postId) updatedComments.add(i)
        }
        return updatedComments.toList()
    }
}