package com.voronin.listapp.postList.client

import retrofit2.Retrofit

/**
 * Implementation of {@link PostClient}
 */
class PostClientImpl(retrofit: Retrofit) : PostClient {

    private val contributorApi = retrofit.create(PostApi::class.java)

    override suspend fun getContributors(limit: Int, offset: Int) =
        ArrayList(contributorApi.getPosts(limit, offset))

}