package com.example.nav_blog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.nav_blog.model.PostData
import com.example.nav_blog.model.UsersData
import com.example.nav_blog.util.CheckField.notNull
import com.example.nav_blog.view_model.PostViewModel
import kotlinx.android.synthetic.main.fragment_add_post.*
import kotlinx.android.synthetic.main.fragment_add_post.view.*
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * Fragment to Add Post to the Db
 */
class AddPost : Fragment() {

    /**
     * @ViewModel this is the Instance of the ViewModel used for this Fragment to send  to  DB
     */
    lateinit var viewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        var view = inflater.inflate(R.layout.fragment_add_post, container, false)

        /**
         * btn_sendPost to add comment to Db
         */
        view.btn_sendPost.setOnClickListener {
            addPostToDatabase(it)
        }
        return view
    }

    private fun addPostToDatabase(view: View) {

        /**
         * Getting Data from the EditText
         */
        var articleBody = article.text.toString()
        var articleTitle = writer_title.text.toString()


        var articleCheck = notNull(articleBody)
        var titleCheck = notNull(articleTitle)

        /**
         * Validation check using the CheckField Object
         */

        if (articleCheck && titleCheck) {


            /**
             * Add new Post to PostDatabase
             */
            var newPost = PostData(articleBody, null, articleTitle, 11)
            viewModel.addPost(newPost)



            Toast.makeText(requireContext(), "successfully_Added ", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addPost_to_mainFragment)


        } else {
            if (!articleCheck) {
                article.text?.clear()
                article.setError("Field must be field")
            }
            if (!titleCheck) {
                writer_title.text?.clear()
                writer_title.setError("Field must be field")
            }

        }


    }
}
