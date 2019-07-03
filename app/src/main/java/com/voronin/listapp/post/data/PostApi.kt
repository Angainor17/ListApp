package com.voronin.listapp.post.data

import com.voronin.listapp.post.data.models.Post
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("/wp-json/mob/posts")
    fun getList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int

    ): Deferred<ArrayList<Post>>

}