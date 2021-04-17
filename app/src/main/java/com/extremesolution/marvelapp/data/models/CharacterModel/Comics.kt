package com.extremesolution.marvelapp.data.models.CharacterModel

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)