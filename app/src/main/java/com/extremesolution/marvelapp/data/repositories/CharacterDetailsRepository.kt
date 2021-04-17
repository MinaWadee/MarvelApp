package com.extremesolution.marvelapp.data.repositories

import com.extremesolution.marvelapp.data.network.ApiInterface
import com.extremesolution.marvelapp.ui.base.BaseRepository

class CharacterDetailsRepository(private val api:ApiInterface):BaseRepository() {

    suspend fun getComicsList(characterId: String) = safeApiCall {
        api.characterComics(characterId)
    }

    suspend fun getEventsList(characterId: String) = safeApiCall {
        api.characterEvents(characterId)
    }

    suspend fun getSeriesList(characterId: String) = safeApiCall {
        api.characterSeries(characterId)
    }

    suspend fun getStoriesList(characterId: String) = safeApiCall {
        api.characterStories(characterId)
    }

}