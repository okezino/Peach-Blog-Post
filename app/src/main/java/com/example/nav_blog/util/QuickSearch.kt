package com.example.nav_blog.util

import com.example.nav_blog.model.PostData

/**
 * Method to search for words
 */
object SearchForWords {

    fun search(s : String, post : MutableList<PostData>) : MutableList<PostData>{
        var listData = mutableListOf<PostData>()
        for (i in post) if(i.title.contains(s,ignoreCase = true) || i.body.contains(s,ignoreCase = true) ) listData.add(i)
        return listData
    }
}