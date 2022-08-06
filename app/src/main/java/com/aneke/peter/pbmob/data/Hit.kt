package com.aneke.peter.pbmob.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hit(

    @PrimaryKey
    val id: Int = 0,

    val collections: Int = 0,
    val comments: Int = 0,
    val downloads: Int = 0,
    val largeImageURL: String = "",
    val likes: Int = 0,
    val pageURL: String = "",
    val previewURL: String = "",
    val tags: String = "",
    val type: String = "",
    val user: String = "",
    val userImageURL: String = "",
    val user_id: Int = 0,
    val views: Int = 0,
    val webformatURL: String = "",

    var searchQuery : String = ""
)