package com.example.nav_blog.view.Adapter

import android.view.View

/**
 * Interface to implement on click function
 */
interface OnNodeClick {
    fun nodeClick(id : Int?, title : String, name : String, body : String, view : View)
}