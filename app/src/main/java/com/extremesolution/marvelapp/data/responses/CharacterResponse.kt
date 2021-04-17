package com.extremesolution.marvelapp.data.responses

import com.extremesolution.marvelapp.data.models.CharacterModel.CharacterData

data class CharacterResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: CharacterData,
    val etag: String,
    val status: String
)