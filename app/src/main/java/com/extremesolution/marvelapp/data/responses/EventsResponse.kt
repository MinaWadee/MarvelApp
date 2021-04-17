package com.extremesolution.marvelapp.data.responses

import com.extremesolution.marvelapp.data.models.EventsModels.EventsData

data class EventsResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: EventsData,
    val etag: String,
    val status: String
)