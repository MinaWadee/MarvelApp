package com.extremesolution.marvelapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.appActivities.MainActivity
import com.extremesolution.marvelapp.data.repositories.MarvelCharactersRepository
import com.extremesolution.marvelapp.data.models.CharacterModel.CharacterResult
import com.extremesolution.marvelapp.data.network.ApiInterface
import com.extremesolution.marvelapp.data.network.Resource
import com.extremesolution.marvelapp.data.network.handleApiError
import com.extremesolution.marvelapp.data.network.visible
import com.extremesolution.marvelapp.databinding.MarvelCharactersLayoutBinding
import com.extremesolution.marvelapp.ui.adapters.CharactersAdapter
import com.extremesolution.marvelapp.ui.base.BaseFragment
import com.extremesolution.marvelapp.ui.home.ViewModels.MarvelCharactersViewModel
import com.extremesolution.marvelapp.ui.search.SearchFragment
import com.jcodecraeer.xrecyclerview.XRecyclerView.LoadingListener
import kotlinx.android.synthetic.main.marvel_characters_layout.*


class MarvelCharactersFragment :
    BaseFragment<MarvelCharactersViewModel, MarvelCharactersLayoutBinding, MarvelCharactersRepository>() {

    var currentPage: Int = 0
    var lastPage: Int = -1

    //var characterList: List<Result>? = null
    val characterList: MutableList<CharacterResult> = ArrayList()
    var Adapter: CharactersAdapter? = null

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        Glide.with(requireContext()).load(R.drawable.search_icon).into(binding.searchButton)


        viewModel.characterResponse.observe(viewLifecycleOwner, Observer {
            // binding.MainLoadingRL.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    when (it.value.code) {
                        200 -> {
                            lastPage = it.value.data.total / it.value.data.limit
                            updateMarvelList(it.value.data.results)
                        }
                        else -> {
                            error(it.value.status)
                        }

                    }
                    binding.MainLoadingRL.visible(false)
                }

                is Resource.Loading -> {
                    binding.MainLoadingRL.visible(true)
                }

                is Resource.Failure -> handleApiError(it) {
                    loadData(currentPage.toString().trim())
                }
            }
        })


        //To set loading style of load more and refresh
        binding.MarvelCharactersListRVID.setLoadingMoreProgressStyle(3)
        binding.MarvelCharactersListRVID.setRefreshProgressStyle(3)
        //Recyclerview load more code
        binding.MarvelCharactersListRVID.setLoadingListener(object : LoadingListener {
            override fun onRefresh() {
                currentPage = 0
                lastPage = -1
                loadData(currentPage.toString().trim())
                binding.MarvelCharactersListRVID.setNoMore(false)
                binding.MarvelCharactersListRVID.setLoadingMoreEnabled(true)
            }

            override fun onLoadMore() {
                if (currentPage <= lastPage) {
                    loadData(currentPage.toString().trim())
                } else {
                    binding.MarvelCharactersListRVID.setNoMore(false)
                }
            }
        })

        search_button.setOnClickListener {
            replace(SearchFragment.newInstance())
        }

        //Call the API
        loadData(currentPage.toString().trim())
    }

    private fun loadData(currentPage: String) {
        viewModel.characterList(currentPage, "10")
    }

    private fun replace(fragment: Fragment) {
        (activity as MainActivity).replaceFragment(fragment, R.id.rlParent,
            inAnimRes = R.anim.slide_in_right.takeIf { true } ?: R.anim.slide_in_left,
            outAnimRes = R.anim.slide_out_left.takeIf { true }
                ?: R.anim.slide_out_right)
    }


    private fun updateMarvelList(list: List<CharacterResult>) {

        if (currentPage == 0) {
            characterList.addAll(list)

            MarvelCharactersListRVID.apply {
                var adapter = CharactersAdapter(
                    activity?.applicationContext!!,
                    characterList, activity as MainActivity
                )
                MarvelCharactersListRVID.adapter = adapter
                Adapter = adapter
            }

            MarvelCharactersListRVID.refreshComplete()

        } else {
            characterList.addAll(list)
        }

        Adapter?.notifyDataSetChanged()
        MarvelCharactersListRVID.loadMoreComplete()

        if (currentPage <= lastPage) {
            currentPage += 1
        }
    }


    override fun getViewModel(): Class<MarvelCharactersViewModel> =
        MarvelCharactersViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        MarvelCharactersLayoutBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): MarvelCharactersRepository =
        MarvelCharactersRepository(apiClient.buildApi(ApiInterface::class.java))
}