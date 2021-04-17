package com.extremesolution.marvelapp.data.models.StoriesModels

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)