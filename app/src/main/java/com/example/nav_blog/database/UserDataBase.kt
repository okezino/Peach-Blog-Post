package com.example.nav_blog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nav_blog.model.UsersData
/**
 * Creating Instance of the User DataBase
 */
@Database(entities = [UsersData::class],version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    /**
     * Creating an Instance of the UserDao Interface
     */
    abstract fun UserDao() : UserDao

    /**
     * Creating an Instance of the CommentDataBase using the function getUserDataBase
     */
    companion object{
        @Volatile
        private var INSTANCE : UserDataBase? = null

        fun getUserData(context:Context):UserDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "user_database"
                ).build()

                INSTANCE = instance

                return instance
            }
        }
    }




}