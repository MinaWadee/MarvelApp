package com.extremesolution.marvelapp.data.models.characterList.SeriesModel

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)