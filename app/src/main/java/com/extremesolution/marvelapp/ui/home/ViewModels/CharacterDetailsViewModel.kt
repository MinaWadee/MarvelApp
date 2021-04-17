package com.extremesolution.marvelapp.ui.home.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.extremesolution.marvelapp.data.Repositories.CharacterDetailsRepository
import com.extremesolution.marvelapp.data.network.Resource
import com.extremesolution.marvelapp.data.responses.SeriesResponse
import com.extremesolution.marvelapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get

class CharacterDetailsViewModel(private val repository: CharacterDetailsRepository) :
    BaseViewModel(repository) {

    private val _characterSeriesResponse: MutableLiveData<Resource<SeriesResponse>> =
        MutableLiveData()
    val seriesResponse : LiveData<Resource<SeriesResponse>>
        get() = _characterSeriesResponse


    fun getSeriesList(characterId:String) = viewModelScope.launch {
        _characterSeriesResponse.value = Resource.Loading
        _characterSeriesResponse.value = repository.getSeriesList(characterId)
    }
}