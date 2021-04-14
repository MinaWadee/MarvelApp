package com.extremesolution.marvelapp.ui.home.ViewModels

import com.extremesolution.marvelapp.data.Repositories.MarvelCharacterDetailsRepository
import com.extremesolution.marvelapp.ui.base.BaseViewModel

class MarvelCharacterDetailsViewModel(private val repository: MarvelCharacterDetailsRepository) :
    BaseViewModel(repository)