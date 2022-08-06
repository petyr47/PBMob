package com.aneke.peter.pbmob.network.model

import com.aneke.peter.pbmob.data.Hit

data class SearchImageResponse(
    val hits: List<Hit> = listOf(),
    val total: Int = 0,
    val totalHits: Int = 0,
    val success : Boolean = true,
    val message : String = ""
)