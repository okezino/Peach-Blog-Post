package com.example.nav_blog.util

/**
 * Get Commenters Name From the Email Addresses
 */
object getCommentName {

    fun name(s : String) : String{
        return s.takeWhile { it != '@' }

    }
}