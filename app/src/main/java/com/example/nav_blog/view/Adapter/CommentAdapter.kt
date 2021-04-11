package com.example.nav_blog.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nav_blog.R
import com.example.nav_blog.model.CommentData
import com.example.nav_blog.util.getCommentName.name
import kotlinx.android.synthetic.main.comment_layout.view.*

/**
 * CommentAdapter to inflate the Activity RecyclerView
 */
class CommentAdapter() : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    /**
     * This is the CommentList of the Adapater to inflate the List of comment
     */
    var CommentList = mutableListOf<CommentData>()

    /**
     * Adapter inner Class
     */
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var serial : TextView = itemView.serialNumber
        var commentEmail : TextView = itemView.commenterEmail
        var comment : TextView = itemView.commentBody

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.comment_layout,parent,false)
        return ViewHolder(view)
    }

    /**
     * Set the count of the Item on the commentList
     */
    override fun getItemCount(): Int {
        return  CommentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.comment.text = CommentList[position].body
        holder.serial.text = (1 + position).toString()
        holder.commentEmail.text = name(CommentList[position].email)
    }

    /**
     *@ListComment Observing function to check and update the CommentList once the value Changes
     */

    fun listComment(comment : List<CommentData>){
      CommentList= comment.toMutableList()
        notifyDataSetChanged()
    }


}