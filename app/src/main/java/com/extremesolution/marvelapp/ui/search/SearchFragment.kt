package com.extremesolution.marvelapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.data.Repositories.SearchRepository
import com.extremesolution.marvelapp.data.network.ApiInterface
import com.extremesolution.marvelapp.databinding.SearchLayoutBinding
import com.extremesolution.marvelapp.ui.adapters.MarvelTypesAdapter
import com.extremesolution.marvelapp.ui.adapters.SearchResultAdapter
import com.extremesolution.marvelapp.ui.base.BaseFragment
import com.extremesolution.marvelapp.ui.home.MarvelCharactersFragment
import kotlinx.android.synthetic.main.character_details_layout.*
import kotlinx.android.synthetic.main.search_layout.*


class SearchFragment : BaseFragment<SearchViewModel, SearchLayoutBinding, SearchRepository>() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CancelSearchTVID.setOnClickListener {
            childFragmentManager
                .beginTransaction()
                .add(R.id.SearchMainLayoutID, MarvelCharactersFragment())
                .addToBackStack(null)
                .commit()
        }

        Glide.with(requireContext()).load(R.drawable.search_icon).into(search_button)

        getSearchResult()
    }

    private fun getSearchResult() {
        SearchResultRV.apply {
            adapter = SearchResultAdapter(activity?.applicationContext!!/*, list*/)
            SearchResultRV.adapter = adapter
        }
    }

    override fun getViewModel(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        SearchLayoutBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): SearchRepository =
        SearchRepository(apiClient.buildApi(ApiInterface::class.java))
}