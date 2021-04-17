package com.extremesolution.marvelapp.data.models.characterList.SeriesModel

data class SeriesData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<SeriesResult>,
    val total: Int
)