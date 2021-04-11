package com.example.nav_blog.util

/**
 * Validate the input field  is not null
 */
object CheckField {

    fun notNull(s : String) : Boolean{
        return s.isNotEmpty()
    }

}