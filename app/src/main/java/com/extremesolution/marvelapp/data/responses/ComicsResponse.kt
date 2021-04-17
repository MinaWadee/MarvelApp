package com.extremesolution.marvelapp.data.responses

import com.extremesolution.marvelapp.data.models.ComicsModels.ComicsData

data class ComicsResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: ComicsData,
    val etag: String,
    val status: String
)