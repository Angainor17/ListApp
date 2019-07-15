package com.voronin.listapp.postList.client

import com.voronin.listapp.postList.models.Post

interface PostClient {

    suspend fun getContributors(limit: Int, offset: Int): ArrayList<Post>

}