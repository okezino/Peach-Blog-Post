package com.example.nav_blog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nav_blog.model.CommentData

/**
 * Creating Instance of the Comment DataBase
 */
@Database(entities = [CommentData::class],version = 1, exportSchema = false)
abstract class CommentDataBase : RoomDatabase() {

    /**
     * Creating an Instance of the CommentDao Interface
     */
    abstract fun CommentDao() : CommentDao

    /**
     * Creating an Instance of the CommentDataBase using the function getCommentDataBase
     */
    companion object{
        @Volatile
        private  var INSTANCE : CommentDataBase? = null

        fun getCommentDataBase(context: Context) : CommentDataBase{

            val tempComment = INSTANCE
            if(tempComment != null){
                
                return tempComment
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CommentDataBase::class.java,
                    "comment_database"
                ).build()

                INSTANCE = instance

                return instance
            }
        }
    }
}