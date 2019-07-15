package com.voronin.listapp

import com.voronin.listapp.postList.client.PostClient
import com.voronin.listapp.postList.client.PostClientImpl
import com.voronin.listapp.postList.client.API_BASE_URL
import com.voronin.listapp.net.NetRequestTest
import com.voronin.listapp.utils.createRetrofit
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ApiTest : NetRequestTest() {

    private val retrofit = createRetrofit(API_BASE_URL)
    private val client: PostClient = PostClientImpl(retrofit)

    /**
     * Check if request is correct
     */
    @Test
    fun getContributorsTest() = runBlocking {
        val list = client.getContributors(10, 0)
        assert(list.isNotEmpty())
    }
}