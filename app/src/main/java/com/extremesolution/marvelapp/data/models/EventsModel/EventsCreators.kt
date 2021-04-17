package com.extremesolution.marvelapp.data.models.EventsModel

data class EventsCreators(
    val available: Int,
    val collectionURI: String,
    val items: List<EventsItem>,
    val returned: Int
)