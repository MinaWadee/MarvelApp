package com.extremesolution.marvelapp.data.models.ComicsModels

data class ComicsData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ComicsResult>,
    val total: Int
)