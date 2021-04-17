package com.extremesolution.marvelapp.data.models.characterList.SeriesModel

data class SeriesResult(
    val characters: SeriesCharacters,
    val comics: SeriesComics,
    val creators: SeriesCreators,
    val description: Any,
    val endYear: Int,
    val events: SeriesEvents,
    val id: Int,
    val modified: String,
    val next: Any,
    val previous: Any,
    val rating: String,
    val resourceURI: String,
    val startYear: Int,
    val stories: SeriesStories,
    val thumbnail: SeriesThumbnail,
    val title: String,
    val type: String,
    val urls: List<SeriesUrl>
)