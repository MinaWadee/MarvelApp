package com.extremesolution.marvelapp.data.models.CharacterModel

data class CharacterEvents(
    val available: Int,
    val collectionURI: String,
    val characterItems: List<CharacterItem>,
    val returned: Int
)