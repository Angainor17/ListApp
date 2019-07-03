package com.voronin.listapp.app

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.voronin.listapp.post.data.client.PostClient
import com.voronin.listapp.post.data.client.PostClientImpl
import com.voronin.listapp.utils.createRetrofit
import retrofit2.Retrofit

val POST_TAG = "post"

class App : Application() {

    val kodein = Kodein {
        bind<Retrofit>(tag = POST_TAG) with singleton { createRetrofit(POST_BASE_URL) }
        bind<PostClient>() with singleton { PostClientImpl(instance()) }
    }
}