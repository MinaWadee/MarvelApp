package com.extremesolution.marvelapp.ui.home.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.extremesolution.marvelapp.data.Repositories.MarvelCharactersRepository
import com.extremesolution.marvelapp.data.network.Resource
import com.extremesolution.marvelapp.data.responses.CharacterResponse
import com.extremesolution.marvelapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MarvelCharactersViewModel(private val repository: MarvelCharactersRepository) :
    BaseViewModel(repository) {

   private val _characterReponse: MutableLiveData<Resource<CharacterResponse>> =
        MutableLiveData()
    val characterResponse: LiveData<Resource<CharacterResponse>>
        get() = _characterReponse

    fun characterList(page: String, limit: String) = viewModelScope.launch {
        _characterReponse.value = Resource.Loading
        _characterReponse.value = repository.characterList(page, limit)
    }
}