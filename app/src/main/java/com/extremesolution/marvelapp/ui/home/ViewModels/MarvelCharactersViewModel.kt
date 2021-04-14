package com.extremesolution.marvelapp.ui.home.ViewModels

import com.extremesolution.marvelapp.data.Repositories.MarvelCharactersRepository
import com.extremesolution.marvelapp.ui.base.BaseViewModel

class MarvelCharactersViewModel(private val repository: MarvelCharactersRepository) :
    BaseViewModel(repository) {

}