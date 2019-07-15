package com.voronin.listapp.app

import android.app.Application
import com.voronin.listapp.postList.client.PostClient
import com.voronin.listapp.postList.client.PostClientImpl
import com.voronin.listapp.postList.client.API_BASE_URL
import com.voronin.listapp.navActivity.viewModel.NavViewModel
import com.voronin.listapp.utils.KodeinViewModelFactory
import com.voronin.listapp.utils.createRetrofit
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.eagerSingleton
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

class App : Application() {

    companion object {
        val kodein = Kodein {
            bind<Retrofit>() with singleton { createRetrofit(API_BASE_URL) }
            bind<PostClient>() with singleton { PostClientImpl(instance()) }
            bind<KodeinViewModelFactory>() with eagerSingleton  { KodeinViewModelFactory(kodein) }
            bind<NavViewModel>() with singleton { NavViewModel() }
        }
    }
}