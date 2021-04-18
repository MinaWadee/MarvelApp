package com.extremesolution.marvelapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.data.models.CharacterModel.CharacterResult
import com.extremesolution.marvelapp.data.network.ApiInterface
import com.extremesolution.marvelapp.data.repositories.SearchRepository
import com.extremesolution.marvelapp.databinding.SearchLayoutBinding
import com.extremesolution.marvelapp.ui.adapters.SearchResultAdapter
import com.extremesolution.marvelapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.search_layout.*


class SearchFragment : BaseFragment<SearchViewModel, SearchLayoutBinding, SearchRepository>() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    var list: ArrayList<CharacterResult>? = null
    var filteredList: MutableList<CharacterResult> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = arguments?.getSerializable("SearchList") as ArrayList<CharacterResult>

        SearchFiledET.addTextChangedListener {
            filteredList.clear()
            for (i in 0 until list!!.size) {
                if (list!![i].name.contains(it.toString()) && it.toString() != "") {
                    filteredList.add(list!![i])
                }
            }
            if (it.toString() == "") {
                filteredList.clear()
            }
            val writtenCharacters = it.toString()
            getSearchResult(filteredList as ArrayList<CharacterResult>, writtenCharacters)
        }


        CancelSearchTVID.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        Glide.with(requireContext()).load(R.drawable.search_icon).into(search_button)

    }

    private fun getSearchResult(filteredList: ArrayList<CharacterResult>,
        writtenCharacters: String) {
        SearchResultRV.apply {
            adapter = SearchResultAdapter(activity?.applicationContext!!,
                filteredList, writtenCharacters)
            SearchResultRV.adapter = adapter
        }
    }

    override fun getViewModel(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        SearchLayoutBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): SearchRepository =
        SearchRepository(apiClient.buildApi(ApiInterface::class.java))
}

