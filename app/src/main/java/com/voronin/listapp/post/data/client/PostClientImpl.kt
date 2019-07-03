package com.voronin.listapp.post.data.client

import com.voronin.listapp.post.data.PostApi
import retrofit2.Retrofit

/**
 * TODO
 */
class PostClientImpl(retrofit: Retrofit) : PostClient {

    val api: PostApi = retrofit.create(PostApi::class.java)


}