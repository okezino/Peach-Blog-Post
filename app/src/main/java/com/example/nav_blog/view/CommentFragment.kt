package com.example.nav_blog.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nav_blog.R
import com.example.nav_blog.model.CommentData
import com.example.nav_blog.util.CheckField.notNull
import com.example.nav_blog.util.getCommentsFromDB
import com.example.nav_blog.util.getCommentsFromDB.getArray
import com.example.nav_blog.view.Adapter.CommentAdapter
import com.example.nav_blog.view_model.CommentViewModel
import com.example.nav_blog.view_model.PostViewModel
//import com.example.nav_blog.view_model.CommentViewModel
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_comment.view.*

/**
 * Comment Fragment for Display about the Articles and the Comments
 */

class CommentFragment : Fragment() {

    /**
     * @args this is the ArgValue gotten from the MainFragment containing Data needed to inflate the view
     * @ComAdapter this is the Adapter for the comment RecyclerView
     * @ViewModel this is the Instance of the ViewModel used for this Fragment to get and Manage Data from the DB
     */
    val args: CommentFragmentArgs by navArgs()
    var ComAdapter = CommentAdapter()
    lateinit var viewModel: CommentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Initializing the viewModel
         * Calling the addBulkComment function to update the Database with all the Data from the Api
         */
        viewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
        viewModel.addBulkComment()

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_comment, container, false)

        /**
         * initializing the recyclerView and setting its properties
         */
        var review = view.recycler_view
        review.adapter = ComAdapter
        review.layoutManager = LinearLayoutManager(requireContext())

        /**
         * Observant all the comment from the Database and updating the List in the Adapter
         */
        viewModel.readAllCommentData.observe(viewLifecycleOwner, Observer {

            ComAdapter.listComment(getArray(it, args.id))
        })

        /**
         * add comment button to move to the Fragment to Add comment
         */

        view.btn_addComment.setOnClickListener {
            var action = CommentFragmentDirections.actionCommentFragmentToAddComment(args.id)
            Navigation.findNavController(it).navigate(action)
        }

        return view
    }

    /**
     * Updating the UI with the values from the Main Fragment
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleBody.text = args.body
        post_title.text = args.title
        authorName.text = args.name

    }

}