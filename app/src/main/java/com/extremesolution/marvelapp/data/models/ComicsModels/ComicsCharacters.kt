package com.extremesolution.marvelapp.data.models.ComicsModels

data class ComicsCharacters(
    val available: Int,
    val collectionURI: String,
    val items: List<ComicsItem>,
    val returned: Int
)