package com.extremesolution.marvelapp.data.models.CharacterModel

data class CharacterSeries(
    val available: Int,
    val collectionURI: String,
    val characterItems: List<CharacterItem>,
    val returned: Int
)