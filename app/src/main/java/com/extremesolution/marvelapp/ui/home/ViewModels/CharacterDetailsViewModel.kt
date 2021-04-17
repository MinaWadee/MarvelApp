package com.extremesolution.marvelapp.ui.home.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.extremesolution.marvelapp.data.repositories.CharacterDetailsRepository
import com.extremesolution.marvelapp.data.network.Resource
import com.extremesolution.marvelapp.data.responses.ComicsResponse
import com.extremesolution.marvelapp.data.responses.EventsResponse
import com.extremesolution.marvelapp.data.responses.SeriesResponse
import com.extremesolution.marvelapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(private val repository: CharacterDetailsRepository) :
    BaseViewModel(repository) {

    private val _comicsResponse: MutableLiveData<Resource<ComicsResponse>> =
        MutableLiveData()
    val comicsResponse: LiveData<Resource<ComicsResponse>>
        get() = _comicsResponse

    private val _eventsResponse: MutableLiveData<Resource<EventsResponse>> =
        MutableLiveData()
    val eventsResponse: LiveData<Resource<EventsResponse>>
        get() = _eventsResponse


    private val _characterSeriesResponse: MutableLiveData<Resource<SeriesResponse>> =
        MutableLiveData()
    val seriesResponse: LiveData<Resource<SeriesResponse>>
        get() = _characterSeriesResponse


    fun getMarvelSpecialCharacterList(characterId: String) = viewModelScope.launch {
        _characterSeriesResponse.value = Resource.Loading
        _characterSeriesResponse.value = repository.getSeriesList(characterId)

        _comicsResponse.value = Resource.Loading
        _comicsResponse.value = repository.getComicsList(characterId)

        _eventsResponse.value = Resource.Loading
        _eventsResponse.value = repository.getEventsList(characterId)
    }
}