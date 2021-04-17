package com.extremesolution.marvelapp.data.models.StoriesModels

data class StoriesCharacters(
    val available: Int,
    val collectionURI: String,
    val items: List<StoriesItem>,
    val returned: Int
)