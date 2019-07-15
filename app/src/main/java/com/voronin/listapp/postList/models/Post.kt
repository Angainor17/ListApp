package com.voronin.listapp.postList.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

const val POST_TAG = "post"

data class Post(
    val id: Long,
    val date: Long,
    val title: String,
    val text: String,
    @SerializedName("title_image") val titleImage: String
) : Serializable