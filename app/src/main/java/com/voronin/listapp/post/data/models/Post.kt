package com.voronin.listapp.post.data.models

import com.google.gson.annotations.SerializedName

/**
 * TODO
 */
class Post(
    val id: Long,
    val date: Long,
    val title: String,
    val text: String,
    @SerializedName("title_image") val titleImage: String


)