package com.example.nav_blog.model

import androidx.lifecycle.LiveData
import com.example.nav_blog.database.CommentDao
import com.example.nav_blog.database.PostDao
import com.example.nav_blog.database.UserDao

class MainRepository(val postDao: PostDao, val commentDao:CommentDao, val userDao : UserDao) {

    /**
     * @readAllData obtaining data from the PostDataBase using the PostDao
     * @readAllCommentData obtaining data from the CommentDataBase using the CommentDao
     * @readAllUserData obtaining data from the PostDataBase using the UserDao
     */

    val readAllData : LiveData<List<PostData>> = postDao.readAllPostData()
    val readAllCommentData : LiveData<List<CommentData>> = commentDao.getAllComments()
    val readAllUserData : LiveData<List<UsersData>> = userDao.getAllUsers()

    /**
     * Add single postData in the PostDataBase using the PostDao
     */
    suspend fun addPost(post : PostData) {
          postDao.insertPost(post)
    }

    /**
     * Add Bulk postData in the PostDataBase using the PostDao from Api
     */

    suspend fun addBulkPost(){
        val response = MyRetrofitBuilder.apiService.getPosts()
        postDao.insertBulkPost(response)
    }

    /**
     * Add single UsersData in the UsersDataBase using the UserDao
     */
    suspend fun getUserData(user : UsersData){
        userDao.insertUser(user)
    }
    /**
     * Add Bulk UserData in the UserDataBase using the UserDao from Api
     */

    suspend fun getUsersData(){
        val res =  MyRetrofitBuilder.apiService.getUsers()
        userDao.insertBulkUsers(res)
    }
    /**
     * Add Bulk CommentData in the CommentDataBase using the CommentDao from Api
     */

    suspend fun getComments(){
        val res =  MyRetrofitBuilder.apiService.getComments()
        commentDao.insertBulkComment(res)
    }

    /**
     * Add single CommentData in the CommentDataBase using the CommentDao
     */
    suspend fun createNewComment(comment: CommentData) {

        commentDao.insertComment(comment)
    }
}