package com.example.nav_blog.view.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nav_blog.R
import com.example.nav_blog.model.PostData
import com.example.nav_blog.model.UsersData
import com.example.nav_blog.util.ReadTimeValue
import com.example.nav_blog.util.getUserDetails
import kotlinx.android.synthetic.main.post_layout.view.*

/**
 * MainActivity Adapter to inflate the Activity RecyclerView
 */
 class MainActivityAdapter( var onClickView : OnNodeClick) : RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

    var updatedPostData =  mutableListOf<PostData>()
    var updatedUserProfile =  mutableListOf<UsersData>()


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var serial : TextView = itemView.serial
        var writerName : TextView = itemView.writerName
        var posTitle : TextView = itemView.postTitle
        var readTime : TextView = itemView.readTime

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.post_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  updatedPostData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var readTimeData = ReadTimeValue.realTimeValue(updatedPostData[position].body)
        var articleTitle = updatedPostData[position].title
        var articleBody = updatedPostData[position].body
        var authorName = getUserDetails.getUserName(updatedPostData[position],updatedUserProfile)


        holder.serial.text = updatedPostData[position].id.toString()
        holder.posTitle.text = articleTitle
        holder.writerName.text = authorName
        holder.readTime.text = "$readTimeData min read"
        holder.posTitle.setOnClickListener {
            onClickView.nodeClick(updatedPostData[position].id,articleTitle,authorName,articleBody,it)
        }


    }

    /**
     *@updateUsers Observing function to check and update the UsersList once the value Changes
     */
     fun updateUsers(users : List<UsersData>){
        updatedUserProfile = users.toMutableList()
         notifyDataSetChanged()
    }

    /**
     *@updatePost Observing function to check and update the PostList once the value Changes
     */
    fun updatePost(post : List<PostData>){
        updatedPostData = post.toMutableList()
        notifyDataSetChanged()
    }






}