package com.extremesolution.marvelapp.ui.base

import androidx.lifecycle.ViewModel
import com.extremesolution.marvelapp.data.network.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


abstract class BaseViewModel(private val repository: BaseRepository) : ViewModel() {

    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) { repository.logout(api) }

}