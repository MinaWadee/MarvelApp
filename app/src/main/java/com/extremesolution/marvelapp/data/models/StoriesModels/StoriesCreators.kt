package com.extremesolution.marvelapp.data.models.StoriesModels

data class StoriesCreators(
    val available: Int,
    val collectionURI: String,
    val items: List<StoriesItem>,
    val returned: Int
)