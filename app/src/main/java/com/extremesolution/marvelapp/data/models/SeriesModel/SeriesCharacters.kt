package com.extremesolution.marvelapp.data.models.characterList.SeriesModel

data class SeriesCharacters(
    val available: Int,
    val collectionURI: String,
    val items: List<SeriesItem>,
    val returned: Int
)