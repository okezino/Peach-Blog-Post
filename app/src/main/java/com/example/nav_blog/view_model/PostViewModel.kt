package com.example.nav_blog.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nav_blog.database.CommentDataBase
import com.example.nav_blog.database.PostDataBase
import com.example.nav_blog.database.UserDataBase
import com.example.nav_blog.model.MainRepository
import com.example.nav_blog.model.PostData
import com.example.nav_blog.model.UsersData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PostViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Declaring instance of repository and ReadAllPost and ReadAllUsers Data
     */
    private val repository: MainRepository
    val readAllPostData: LiveData<List<PostData>>
    val readAllUsersData: LiveData<List<UsersData>>

    /**
     * Initializing the repository, readAllPostDat and ReadAllUserData in an init block to auto run once the class is active
     */
    init {
        var postDao = PostDataBase.getPostDataBase(application).PostDao()
        var userDao = UserDataBase.getUserData(application).UserDao()
        var commentDao = CommentDataBase.getCommentDataBase((application)).CommentDao()
        repository = MainRepository(postDao, commentDao, userDao)
        readAllPostData = repository.readAllData
        readAllUsersData = repository.readAllUserData
    }

    /**
     * @addBulkPost function to  add a post to the DB from the Api
     */

    fun addBulkPost() {
        viewModelScope.launch {
            repository.addBulkPost()
        }
    }

    /**
     * @addPost function to  add a post to the DB
     */
    fun addPost(post: PostData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPost(post)
        }
    }

    /**
     * @addBulkUser to add bulk users from the APi to the DB
     */
    fun addBulkUser() {
        viewModelScope.launch {
            repository.getUsersData()
        }
    }

    /**
     * @addUser function to  add a users to the DB
     */

    fun addUser(user: UsersData) {
        viewModelScope.launch {
            repository.getUserData(user)
        }
    }


}