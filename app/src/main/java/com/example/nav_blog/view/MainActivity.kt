package com.example.nav_blog.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nav_blog.R
import com.example.nav_blog.view.Adapter.MainActivityAdapter
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * MainActivity Holding the NavHost
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}