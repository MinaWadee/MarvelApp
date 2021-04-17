package com.extremesolution.marvelapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.data.Repositories.CharacterDetailsRepository
import com.extremesolution.marvelapp.data.models.characterList.SeriesModel.Result
import com.extremesolution.marvelapp.data.network.ApiInterface
import com.extremesolution.marvelapp.data.network.Resource
import com.extremesolution.marvelapp.data.network.handleApiError
import com.extremesolution.marvelapp.data.network.visible
import com.extremesolution.marvelapp.databinding.CharacterDetailsLayoutBinding
import com.extremesolution.marvelapp.ui.adapters.MarvelOneCharacterAdapter
import com.extremesolution.marvelapp.ui.base.BaseFragment
import com.extremesolution.marvelapp.ui.home.ViewModels.CharacterDetailsViewModel
import com.extremesolution.marvelapp.ui.search.SearchFragment
import kotlinx.android.synthetic.main.character_details_layout.*


class MarvelCharacterDetailsFragment :
    BaseFragment<CharacterDetailsViewModel, CharacterDetailsLayoutBinding, CharacterDetailsRepository>() {

    companion object {
        fun newInstance() = MarvelCharacterDetailsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CharacterImageID.clipToOutline = true

        DescriptionOfTheCharacterTV.text =  arguments?.getString("Description")
        CharacterNameTV.text =  arguments?.getString("CharacterName")
        Glide.with(requireContext())
            .load(arguments?.getString("CharacterImg"))
            .placeholder(R.drawable.image_placeholder)
            .into(CharacterImageID)

        viewModel.seriesResponse.observe(viewLifecycleOwner, Observer {
            binding.MainLoadingRL.visible(it is Resource.Loading)

            when (it) {
                is Resource.Success -> {
                    initializeMarveTypesAdapter(it.value.data.results)
                }

                is Resource.Loading -> {

                    binding.MainLoadingRL.visible(true)
                }

                is Resource.Failure -> handleApiError(it) {
                    loadData(arguments?.getString("CharacterID"))
                }
            }
        })

        loadData(arguments?.getString("CharacterID"))
    }

    private fun loadData(characterID:String?) {
        viewModel.getSeriesList(characterID!!)
    }

    private fun initializeMarveTypesAdapter(list: List<Result>) {
        /*MarvelTypesRV.apply {
            adapter = MarvelTypesAdapter(activity?.applicationContext!!*//*, list*//*)
            MarvelTypesRV.adapter = adapter
        }*/
        MarvelSpecialTypeRV.apply {
            val adapter = MarvelOneCharacterAdapter(requireContext(), list)
            MarvelSpecialTypeRV.adapter = adapter
        }
    }

    override fun getViewModel(): Class<CharacterDetailsViewModel> =
        CharacterDetailsViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        CharacterDetailsLayoutBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): CharacterDetailsRepository =
        CharacterDetailsRepository(apiClient.buildApi(ApiInterface::class.java))
}