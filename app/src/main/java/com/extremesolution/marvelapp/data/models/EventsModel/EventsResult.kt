package com.extremesolution.marvelapp.data.models.EventsModel

data class EventsResult(
    val characters: EventsCharacters,
    val comics: EventsComics,
    val creators: EventsCreators,
    val description: String,
    val end: String,
    val id: Int,
    val modified: String,
    val next: EventsNext,
    val previous: EventsPrevious,
    val resourceURI: String,
    val series: EventsSeries,
    val start: String,
    val stories: EventsStories,
    val thumbnail: EventsThumbnail,
    val title: String,
    val urls: List<EventsUrl>
)