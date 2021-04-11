package com.example.nav_blog.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nav_blog.database.CommentDataBase
import com.example.nav_blog.database.PostDataBase
import com.example.nav_blog.database.UserDataBase
import com.example.nav_blog.model.CommentData
import com.example.nav_blog.model.MainRepository
import com.example.nav_blog.model.PostData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Declaring instance of repository and ReadAllComment Data
     */
    private val repository: MainRepository
    val readAllCommentData: LiveData<List<CommentData>>

    /**
     * Initializing the repository and ReadAllComment Data in an init block to auto run once the class is active
     */

    init {
        var postDao = PostDataBase.getPostDataBase(application).PostDao()
        var userDao = UserDataBase.getUserData(application).UserDao()
        var commentDao = CommentDataBase.getCommentDataBase((application)).CommentDao()
        repository = MainRepository(postDao, commentDao, userDao)
        readAllCommentData = repository.readAllCommentData
    }


    /**
     * @addComment function to  add a comment to the DB
     */
    fun addComment(comment: CommentData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createNewComment(comment)
        }

    }

    /**
     * @addBulkComment to add bulk comment from the APi to the DB
     */
    fun addBulkComment() {
        viewModelScope.launch {
            repository.getComments()
        }
    }

}