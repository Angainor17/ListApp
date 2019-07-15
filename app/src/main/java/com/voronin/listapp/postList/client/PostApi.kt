package com.voronin.listapp.postList.client

import com.voronin.listapp.postList.models.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("/wp-json/mob/posts")
    fun getPosts(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ArrayList<Post>
}