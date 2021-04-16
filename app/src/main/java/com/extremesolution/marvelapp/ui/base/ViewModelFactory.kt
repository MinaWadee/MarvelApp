package com.extremesolution.marvelapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.extremesolution.marvelapp.data.Repositories.CharacterDetailsRepository
import com.extremesolution.marvelapp.data.Repositories.MarvelCharacterDetailsRepository
import com.extremesolution.marvelapp.data.Repositories.MarvelCharactersRepository
import com.extremesolution.marvelapp.data.Repositories.SearchRepository
import com.extremesolution.marvelapp.databinding.MarvelCharactersDetailsLayoutBinding
import com.extremesolution.marvelapp.ui.home.ViewModels.CharacterDetailsViewModel
import com.extremesolution.marvelapp.ui.home.ViewModels.MarvelCharacterDetailsViewModel
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
            modelClass.isAssignableFrom(MarvelCharacterDetailsViewModel::class.java) -> MarvelCharacterDetailsViewModel(repository as MarvelCharacterDetailsRepository) as T
            modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java) -> CharacterDetailsViewModel(repository as CharacterDetailsRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}