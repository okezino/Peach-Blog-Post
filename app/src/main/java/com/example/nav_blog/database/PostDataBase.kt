package com.example.nav_blog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nav_blog.model.CommentData
import com.example.nav_blog.model.PostData

/**
 * Creating Instance of the PostDataBase
 */
@Database(entities = [PostData:: class, CommentData:: class],version = 1,exportSchema = false)
abstract class PostDataBase : RoomDatabase() {


    /**
     * Creating an Instance of the PostDao Interface
     */
    abstract  fun PostDao() : PostDao

    /**
     * Creating an Instance of the PostDataBase using the function getPostDataBase
     */

    companion object{
        @Volatile
        private var INSTANCE : PostDataBase? = null

        fun getPostDataBase(context : Context) : PostDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostDataBase::class.java,
                    "post_database"
                ).build()

                 INSTANCE = instance

                return instance
            }
        }
    }
}

