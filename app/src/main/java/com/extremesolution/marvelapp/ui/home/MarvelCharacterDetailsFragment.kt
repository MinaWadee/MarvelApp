package com.extremesolution.marvelapp.ui.home

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.data.models.ComicsModels.ComicsResult
import com.extremesolution.marvelapp.data.repositories.CharacterDetailsRepository
import com.extremesolution.marvelapp.data.models.characterList.SeriesModel.SeriesResult
import com.extremesolution.marvelapp.data.network.ApiInterface
import com.extremesolution.marvelapp.data.network.Resource
import com.extremesolution.marvelapp.data.network.handleApiError
import com.extremesolution.marvelapp.data.network.visible
import com.extremesolution.marvelapp.databinding.CharacterDetailsLayoutBinding
import com.extremesolution.marvelapp.ui.adapters.MarvelComicsAdapter
import com.extremesolution.marvelapp.ui.adapters.MarvelSeriesAdapter
import com.extremesolution.marvelapp.ui.base.BaseFragment
import com.extremesolution.marvelapp.ui.home.ViewModels.CharacterDetailsViewModel
import kotlinx.android.synthetic.main.character_details_layout.*


class MarvelCharacterDetailsFragment :
    BaseFragment<CharacterDetailsViewModel, CharacterDetailsLayoutBinding, CharacterDetailsRepository>() {

    companion object {
        fun newInstance() = MarvelCharacterDetailsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CharacterImageID.clipToOutline = true

        DescriptionOfTheCharacterTV.text = arguments?.getString("Description")
        CharacterNameTV.text = arguments?.getString("CharacterName")
        Glide.with(requireContext())
            .load(arguments?.getString("CharacterImg"))
            .placeholder(R.drawable.image_placeholder)
            .into(CharacterImageID)

        blurImage.setBlurRadius(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                100f,
                resources.displayMetrics
            )
        )

        viewModel.comicsResponse.observe(viewLifecycleOwner, Observer {
            binding.MainLoadingRL.visible(it is Resource.Loading)

            when (it) {
                is Resource.Success -> {
                    initializeComicMarveTypesAdapter(it.value.data.results)
                }

                is Resource.Loading -> {

                    binding.MainLoadingRL.visible(true)
                }

                is Resource.Failure -> handleApiError(it) {
                    loadData(arguments?.getString("CharacterID"))
                }
            }
        })

        viewModel.comicsResponse.observe(viewLifecycleOwner, Observer {
            binding.MainLoadingRL.visible(it is Resource.Loading)

            when (it) {
                is Resource.Success -> {
                    initializeComicMarveTypesAdapter(it.value.data.results)
                }

                is Resource.Loading -> {

                    binding.MainLoadingRL.visible(true)
                }

                is Resource.Failure -> handleApiError(it) {
                    loadData(arguments?.getString("CharacterID"))
                }
            }
        })

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

    private fun loadData(characterID: String?) {
        viewModel.getMarvelSpecialCharacterList(characterID!!)
    }

    private fun initializeComicMarveTypesAdapter(list: List<ComicsResult>) {
        MarvelComicsRV.apply {
            val adapter = MarvelComicsAdapter(requireContext(), list)
            MarvelComicsRV.adapter = adapter
        }
    }
    private fun initializeMarveTypesAdapter(list: List<SeriesResult>) {
        MarvelSeriesRV.apply {
            val adapter = MarvelSeriesAdapter(requireContext(), list)
            MarvelSeriesRV.adapter = adapter
        }
    }

    override fun getViewModel(): Class<CharacterDetailsViewModel> =
        CharacterDetailsViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        CharacterDetailsLayoutBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): CharacterDetailsRepository =
        CharacterDetailsRepository(apiClient.buildApi(ApiInterface::class.java))
}