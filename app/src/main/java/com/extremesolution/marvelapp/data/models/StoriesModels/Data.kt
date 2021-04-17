package com.extremesolution.marvelapp.data.models.StoriesModels

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)