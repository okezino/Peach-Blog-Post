package com.example.nav_blog.util

/**
 * Evaluate the length of the string and return estimated read time
 */
object ReadTimeValue {

    fun realTimeValue(s: String) : Int{
        return if(s.length / 25 < 1) 1 else s.length/25
    }

}