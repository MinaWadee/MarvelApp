package com.extremesolution.marvelapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.extremesolution.marvelapp.data.repositories.CharacterDetailsRepository
import com.extremesolution.marvelapp.data.repositories.MarvelCharactersRepository
import com.extremesolution.marvelapp.data.repositories.SearchRepository
import com.extremesolution.marvelapp.ui.home.ViewModels.CharacterDetailsViewModel
import com.extremesolution.marvelapp.ui.home.ViewModels.MarvelCharactersViewModel
import com.extremesolution.marvelapp.ui.search.SearchViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MarvelCharactersViewModel::class.java) -> MarvelCharactersViewModel(repository as MarvelCharactersRepository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(repository as SearchRepository) as T
            modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java) -> CharacterDetailsViewModel(repository as CharacterDetailsRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}