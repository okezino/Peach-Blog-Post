package com.example.nav_blog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nav_blog.model.CommentData
import com.example.nav_blog.util.CheckField
import com.example.nav_blog.view.CommentFragmentArgs
import com.example.nav_blog.view_model.CommentViewModel
import com.example.nav_blog.view_model.PostViewModel
import kotlinx.android.synthetic.main.fragment_add_comment.*
import kotlinx.android.synthetic.main.fragment_add_comment.view.*

/**
 * Fragment to Add comment to the Db
 */
class AddComment : Fragment() {

    /**
     * @args this is the ArgValue gotten from the MainFragment containing Data needed to inflate the view
     * @ViewModel this is the Instance of the ViewModel used for this Fragment to send  to  DB
     */
    val args: AddCommentArgs by navArgs()
    lateinit var viewModel: CommentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_add_comment, container, false)
        viewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
        /**
         * btn_comment to add comment to Db
         */
        view.btn_comment.setOnClickListener {
            addComment(it)
        }

        return view
    }



    private fun addComment(view: View) {

        /**
         * Getting Data from the EditText
         */
        var commentName = comment_name.text.toString()
        var email = email_comment.text.toString()
        var commentBody = user_comment.text.toString()

        /**
         * Validating the input Field
         */

        var nameCheck = CheckField.notNull(commentName)
        var emailCheck = CheckField.notNull(email)
        var commentCheck = CheckField.notNull(commentBody)



        if (nameCheck && emailCheck && commentCheck) {

            var newComment = CommentData(commentBody, email, null, commentName, args.postId)

            /**
             * Sending Data to the ViewModel to the Db
             */

            viewModel.addComment(newComment)
            Toast.makeText(requireContext(), "successfully_Added ", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()

        } else {
            if (!nameCheck) {
                comment_name.text?.clear()
                comment_name.setError("Field must be field")
            }
            if (!emailCheck) {
                email_comment.text?.clear()
                email_comment.setError("Field must be field")
            }
            if (!commentCheck) {
                user_comment.text?.clear()
                user_comment.setError("Field must be field")
            }
        }

    }


}


