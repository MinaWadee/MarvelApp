package com.extremesolution.marvelapp.data.models.StoriesModels

data class StoriesResult(
    val characters: StoriesCharacters,
    val comics: StoriesComics,
    val creators: StoriesCreators,
    val description: String,
    val events: StoriesEvents,
    val id: Int,
    val modified: String,
    val originalIssue: StoriesOriginalIssue,
    val resourceURI: String,
    val series: StoriesSeries,
    val thumbnail: StoriesThumbnail? = null,
    val title: String,
    val type: String
)