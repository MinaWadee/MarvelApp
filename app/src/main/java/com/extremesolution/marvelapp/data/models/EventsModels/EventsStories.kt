package com.extremesolution.marvelapp.data.models.EventsModels

data class EventsStories(
    val available: Int,
    val collectionURI: String,
    val items: List<EventsItem>,
    val returned: Int
)