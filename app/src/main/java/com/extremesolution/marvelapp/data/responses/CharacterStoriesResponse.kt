package com.extremesolution.marvelapp.data.responses

import com.extremesolution.marvelapp.data.models.StoriesModels.StoriesData

data class CharacterStoriesResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: StoriesData,
    val etag: String,
    val status: String
)