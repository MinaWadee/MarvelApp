package com.extremesolution.marvelapp.data.responses

import com.extremesolution.marvelapp.data.models.characterList.SeriesModel.Data

data class SeriesResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)