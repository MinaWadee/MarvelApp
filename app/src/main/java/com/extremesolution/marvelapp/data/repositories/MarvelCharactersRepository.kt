package com.extremesolution.marvelapp.data.repositories

import com.extremesolution.marvelapp.data.network.ApiInterface
import com.extremesolution.marvelapp.ui.base.BaseRepository

class MarvelCharactersRepository(private val api: ApiInterface) : BaseRepository() {

    suspend fun characterList(page: String, limit: String) = safeApiCall {
        api.characterList(page, limit)
    }
}