package com.example.nav_blog.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nav_blog.R
import com.example.nav_blog.util.SearchForWords.search
import com.example.nav_blog.view.Adapter.MainActivityAdapter
import com.example.nav_blog.view.Adapter.OnNodeClick
import com.example.nav_blog.view_model.PostViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * MainFragment for Display about the Article Post
 */
class MainFragment : Fragment(), OnNodeClick {

    /**
     * @MyAdapter this is the Adapter for the post RecyclerView
     * @ViewModel this is the Instance of the ViewModel used for this Fragment to get and Manage Data from the DB
     */
    lateinit var viewModel: PostViewModel
    var MyAdapter = MainActivityAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Initializing the viewModel
         * Calling the addBulkpost and addBulkUser function to update the Database with all the Data from the Api
         */
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        viewModel.addBulkPost()
        viewModel.addBulkUser()

    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        /**
         * initializing the recyclerView and setting its properties
         */

        val re = view.mainRecyclerView
        re.adapter = MyAdapter
        re.layoutManager = LinearLayoutManager(requireContext())

        /**
         * Observant all the Users from the Database and updating the List in the Adapter
         */
        viewModel.readAllUsersData.observe(viewLifecycleOwner, Observer {
            MyAdapter.updateUsers(it)
        })

        /**
         * Observant all the Post from the Database and updating the List in the Adapter
         */
        viewModel.readAllPostData.observe(viewLifecycleOwner, Observer {
            MyAdapter.updatePost(it)
        })
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /**
         * using the SearchFunction from SearcForWords Object Search Button to update the Ui once a word is Types
         */

        btn_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val post = search(s.toString(), viewModel.readAllPostData.value!!.toMutableList())
                MyAdapter.updatePost(post)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val post = search(s.toString(), MyAdapter.updatedPostData)
                MyAdapter.updatePost(post)
            }

        })

        /**
         * Button to Navigate to the Addd Post Fragment
         */

        btn_addPost.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_addPost)
        }

    }


    /**
     * interface from the Adapter to Move to the comment Adapter and pass in some Args
     */
    override fun nodeClick(id: Int?, title: String, name: String, body: String, view: View) {
        val action = MainFragmentDirections.navigateToCommentFragment(id!!, title, name, body)
        Navigation.findNavController(view).navigate(action)
    }
}