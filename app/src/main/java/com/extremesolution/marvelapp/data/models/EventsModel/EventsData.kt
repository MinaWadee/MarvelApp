package com.extremesolution.marvelapp.data.models.EventsModel

data class EventsData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<EventsResult>,
    val total: Int
)