package com.extremesolution.marvelapp.ui.home.ViewModels

import com.extremesolution.marvelapp.data.repositories.MarvelCharacterDetailsRepository
import com.extremesolution.marvelapp.ui.base.BaseViewModel

class MarvelCharacterDetailsViewModel(private val repository: MarvelCharacterDetailsRepository) :
    BaseViewModel(repository)