package com.example.nav_blog.model

import com.example.nav_blog.util.Const.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Creating the Retrofit object to make Api call
 */
object MyRetrofitBuilder {
    val retrofitBuilder : Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
    }


    val apiService  : ApiService by lazy{
        retrofitBuilder.build()
            .create(ApiService::class.java)
    }
}