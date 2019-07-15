package com.voronin.listapp.postDetail.viewModel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voronin.listapp.postList.models.POST_TAG
import com.voronin.listapp.postList.models.Post

class PostDetailViewModel : ViewModel() {

    val liveData = MutableLiveData<Post>()
    var contributor: Post? = null

    fun initialize(it: Bundle?) {
        contributor = it?.get(POST_TAG) as Post
        refreshScreen()
    }

    fun refreshScreen() {
        contributor.let {
            liveData.postValue(it)
        }
    }
}