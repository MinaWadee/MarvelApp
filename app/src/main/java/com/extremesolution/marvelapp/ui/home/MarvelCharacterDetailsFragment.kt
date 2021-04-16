package com.extremesolution.marvelapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.extremesolution.marvelapp.data.Repositories.CharacterDetailsRepository
import com.extremesolution.marvelapp.data.network.ApiInterface
import com.extremesolution.marvelapp.databinding.CharacterDetailsLayoutBinding
import com.extremesolution.marvelapp.ui.adapters.MarvelTypesAdapter
import com.extremesolution.marvelapp.ui.base.BaseFragment
import com.extremesolution.marvelapp.ui.home.ViewModels.CharacterDetailsViewModel
import kotlinx.android.synthetic.main.character_details_layout.*


class MarvelCharacterDetailsFragment :
    BaseFragment<CharacterDetailsViewModel, CharacterDetailsLayoutBinding, CharacterDetailsRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CharacterImageID.clipToOutline = true

        initializeMarveTypesAdapter()
    }

    private fun initializeMarveTypesAdapter() {
        MarvelTypesRV.apply {
            adapter = MarvelTypesAdapter(activity?.applicationContext!!/*, list*/)
            MarvelTypesRV.adapter = adapter
        }
    }

    override fun getViewModel(): Class<CharacterDetailsViewModel> =
        CharacterDetailsViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        CharacterDetailsLayoutBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): CharacterDetailsRepository =
        CharacterDetailsRepository(apiClient.buildApi(ApiInterface::class.java))
}