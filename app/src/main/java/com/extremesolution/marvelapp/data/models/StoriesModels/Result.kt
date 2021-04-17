package com.extremesolution.marvelapp.data.models.StoriesModels

data class Result(
    val characters: Characters,
    val comics: Comics,
    val creators: Creators,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val originalIssue: OriginalIssue,
    val resourceURI: String,
    val series: Series,
    val thumbnail: Thumbnail? = null,
    val title: String,
    val type: String
)