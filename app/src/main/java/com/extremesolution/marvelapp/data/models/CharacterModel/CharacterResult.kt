package com.extremesolution.marvelapp.data.models.CharacterModel

data class CharacterResult(
    val comics: CharacterComics,
    val description: String,
    val events: CharacterEvents,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: CharacterSeries,
    val stories: CharacterStories,
    val thumbnail: CharacterThumbnail,
    val characterUrls: List<CharacterUrl>
)