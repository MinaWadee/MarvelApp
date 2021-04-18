package com.extremesolution.marvelapp.ui.search

import androidx.lifecycle.MutableLiveData
import com.extremesolution.marvelapp.data.models.CharacterModel.CharacterResult
import com.extremesolution.marvelapp.data.repositories.SearchRepository
import com.extremesolution.marvelapp.ui.base.BaseViewModel

class SearchViewModel(private val repository: SearchRepository):BaseViewModel(repository) {


}