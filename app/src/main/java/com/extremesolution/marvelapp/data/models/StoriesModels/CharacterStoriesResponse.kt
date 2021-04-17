package com.extremesolution.marvelapp.data.models.StoriesModels

data class CharacterStoriesResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)