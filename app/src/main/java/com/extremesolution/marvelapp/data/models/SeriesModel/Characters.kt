package com.extremesolution.marvelapp.data.models.characterList.SeriesModel

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)