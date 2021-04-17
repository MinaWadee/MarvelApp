package com.extremesolution.marvelapp.data.models.CharacterModel

data class CharacterStories(
    val available: Int,
    val collectionURI: String,
    val characterItems: List<CharacterItem>,
    val returned: Int
)