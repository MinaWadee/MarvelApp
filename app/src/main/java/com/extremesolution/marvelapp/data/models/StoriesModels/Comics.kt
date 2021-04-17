package com.extremesolution.marvelapp.data.models.StoriesModels

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)