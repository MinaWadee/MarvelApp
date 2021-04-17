package com.extremesolution.marvelapp.data.Repositories

import com.extremesolution.marvelapp.data.network.ApiInterface
import com.extremesolution.marvelapp.ui.base.BaseRepository

class CharacterDetailsRepository(private val api:ApiInterface):BaseRepository() {

    suspend fun getSeriesList(characterId: String) = safeApiCall {
        api.characterSeries(characterId)
    }
}