package com.extremesolution.marvelapp.data.responses

import com.extremesolution.marvelapp.data.models.characterList.SeriesModel.SeriesData

data class SeriesResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: SeriesData,
    val etag: String,
    val status: String
)