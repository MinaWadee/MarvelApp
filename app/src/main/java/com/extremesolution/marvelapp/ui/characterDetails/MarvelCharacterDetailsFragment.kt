package com.extremesolution.marvelapp.ui.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.data.models.ComicsModels.ComicsResult
import com.extremesolution.marvelapp.data.models.EventsModels.EventsResult
import com.extremesolution.marvelapp.data.models.StoriesModels.StoriesResult
import com.extremesolution.marvelapp.data.repositories.CharacterDetailsRepository
import com.extremesolution.marvelapp.data.models.characterList.SeriesModel.SeriesResult
import com.extremesolution.marvelapp.data.network.ApiInterface
import com.extremesolution.marvelapp.data.network.Resource
import com.extremesolution.marvelapp.data.network.handleApiError
import com.extremesolution.marvelapp.data.network.visible
import com.extremesolution.marvelapp.databinding.CharacterDetailsLayoutBinding
import com.extremesolution.marvelapp.ui.adapters.MarvelComicsAdapter
import com.extremesolution.marvelapp.ui.adapters.MarvelEventsAdapter
import com.extremesolution.marvelapp.ui.adapters.MarvelSeriesAdapter
import com.extremesolution.marvelapp.ui.adapters.MarvelStoriesAdapter
import com.extremesolution.marvelapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.character_details_layout.*


class MarvelCharacterDetailsFragment :
    BaseFragment<CharacterDetailsViewModel, CharacterDetailsLayoutBinding, CharacterDetailsRepository>() {

    companion object {
        fun newInstance() =
            MarvelCharacterDetailsFragment()
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

        Glide.with(requireContext()).load(R.drawable.back_arrow_black).into(ArrowBackIV)



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

        viewModel.eventsResponse.observe(viewLifecycleOwner, Observer {
            binding.MainLoadingRL.visible(it is Resource.Loading)

            when (it) {
                is Resource.Success -> {
                    initializeEventsMarveTypesAdapter(it.value.data.results)
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

        viewModel.characterStoriesResponse.observe(viewLifecycleOwner, Observer {
            binding.MainLoadingRL.visible(it is Resource.Loading)

            when (it) {
                is Resource.Success -> {
                    initializeMarveStoriesAdapter(it.value.data.results)
                }

                is Resource.Loading -> {
                    binding.MainLoadingRL.visible(true)
                }

                is Resource.Failure -> handleApiError(it) {
                    loadData(arguments?.getString("CharacterID"))
                }
            }
        })

        BackButtonRl.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        loadData(arguments?.getString("CharacterID"))
    }

    private fun loadData(characterID: String?) {
        viewModel.getMarvelSpecialCharacterList(characterID!!)
    }


    private fun initializeComicMarveTypesAdapter(list: List<ComicsResult>) {
        if (list.size > 0) {
            MarvelComicsRV.apply {
                val adapter = MarvelComicsAdapter(requireContext(), list)
                MarvelComicsRV.adapter = adapter
            }
        } else {
            ComicsPartLL.visibility = GONE
        }
    }

    private fun initializeEventsMarveTypesAdapter(list: List<EventsResult>) {
        if (list.size > 0) {
            MarvelEventsRV.apply {
                val adapter = MarvelEventsAdapter(requireContext(), list)
                MarvelEventsRV.adapter = adapter
            }
        } else {
            EventsPartLL.visibility = GONE
        }
    }

    private fun initializeMarveTypesAdapter(list: List<SeriesResult>) {
        if (list.size > 0) {
            MarvelSeriesRV.apply {
                val adapter = MarvelSeriesAdapter(requireContext(), list)
                MarvelSeriesRV.adapter = adapter
            }
        } else {
            SeriesPartLL.visibility = GONE
        }
    }

    private fun initializeMarveStoriesAdapter(list: List<StoriesResult>) {
        if (list.size > 0) {
            MarvelStoriesRV.apply {
                val adapter = MarvelStoriesAdapter(requireContext(), list)
                MarvelStoriesRV.adapter = adapter
            }
        } else {
            StoriesPartLL.visibility = GONE
        }
    }

    override fun getViewModel(): Class<CharacterDetailsViewModel> =
        CharacterDetailsViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        CharacterDetailsLayoutBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): CharacterDetailsRepository =
        CharacterDetailsRepository(apiClient.buildApi(ApiInterface::class.java))
}