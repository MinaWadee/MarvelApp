package com.extremesolution.marvelapp.ui.home

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import com.bumptech.glide.Glide
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.data.Repositories.MarvelCharactersRepository
import com.extremesolution.marvelapp.data.network.ApiInterface
import com.extremesolution.marvelapp.databinding.MarvelCharactersLayoutBinding
import com.extremesolution.marvelapp.ui.base.BaseFragment
import com.extremesolution.marvelapp.ui.home.ViewModels.MarvelCharactersViewModel


class MarvelCharactersFragment :
    BaseFragment<MarvelCharactersViewModel, MarvelCharactersLayoutBinding, MarvelCharactersRepository>() {

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Glide.with(requireContext()).load(R.drawable.search_icon).into(binding.searchButton)
    }


    override fun getViewModel(): Class<MarvelCharactersViewModel> =
        MarvelCharactersViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        MarvelCharactersLayoutBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): MarvelCharactersRepository =
        MarvelCharactersRepository(apiClient.buildApi(ApiInterface::class.java))
}