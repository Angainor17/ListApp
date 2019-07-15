package com.voronin.listapp.postList.client

import com.voronin.listapp.postList.models.Post

interface PostClient {

    suspend fun getPosts(limit: Int, offset: Int): ArrayList<Post>

}