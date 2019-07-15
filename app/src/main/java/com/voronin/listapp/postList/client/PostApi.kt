package com.voronin.listapp.postList.client

import com.voronin.listapp.postList.models.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("/wp-json/mob/posts")
    suspend fun getPosts(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ArrayList<Post>
}