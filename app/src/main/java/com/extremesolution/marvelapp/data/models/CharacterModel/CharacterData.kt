package com.extremesolution.marvelapp.data.models.CharacterModel

data class CharacterData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<CharacterResult>,
    val total: Int
)