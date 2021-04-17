package com.extremesolution.marvelapp.data.models.StoriesModels

data class StoriesData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<StoriesResult>,
    val total: Int
)