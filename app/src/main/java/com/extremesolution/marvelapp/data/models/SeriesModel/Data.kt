package com.extremesolution.marvelapp.data.models.characterList.SeriesModel

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)