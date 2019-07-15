package com.voronin.listapp.postList.client

import retrofit2.Retrofit

/**
 * Implementation of {@link PostClient}
 */
class PostClientImpl(retrofit: Retrofit) : PostClient {

    private val postApi = retrofit.create(PostApi::class.java)

    override suspend fun getPosts(limit: Int, offset: Int) =
        ArrayList(postApi.getPosts(limit, offset))

}