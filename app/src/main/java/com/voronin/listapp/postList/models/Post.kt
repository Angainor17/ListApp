package com.voronin.listapp.postList.models

import com.google.gson.annotations.SerializedName

const val POST_TAG = "post"

class Post(
    val id: Long,
    val date: Long,
    val title: String,
    val text: String,
    @SerializedName("title_image") val titleImage: String
)