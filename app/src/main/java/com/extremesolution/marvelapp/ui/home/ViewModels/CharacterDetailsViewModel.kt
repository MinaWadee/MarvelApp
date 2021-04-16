package com.extremesolution.marvelapp.ui.home.ViewModels

import com.extremesolution.marvelapp.data.Repositories.CharacterDetailsRepository
import com.extremesolution.marvelapp.ui.base.BaseViewModel

class CharacterDetailsViewModel(private val repository: CharacterDetailsRepository):BaseViewModel(repository) {
}