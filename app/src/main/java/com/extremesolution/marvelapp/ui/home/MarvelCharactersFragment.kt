package com.extremesolution.marvelapp.ui.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.cleveroad.blur_tutorial.BlurTutorial
import com.cleveroad.blur_tutorial.TutorialBuilder
import com.cleveroad.blur_tutorial.state.tutorial.RecyclerItemState
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.appActivities.MainActivity
import com.extremesolution.marvelapp.data.Repositories.MarvelCharactersRepository
import com.extremesolution.marvelapp.data.network.ApiInterface
import com.extremesolution.marvelapp.databinding.MarvelCharactersLayoutBinding
import com.extremesolution.marvelapp.general.RSBlurProcessor
import com.extremesolution.marvelapp.ui.adapters.CharactersAdapter
import com.extremesolution.marvelapp.ui.base.BaseFragment
import com.extremesolution.marvelapp.ui.home.ViewModels.MarvelCharactersViewModel
import com.extremesolution.marvelapp.ui.search.SearchFragment
import com.jcodecraeer.xrecyclerview.XRecyclerView.LoadingListener
import kotlinx.android.synthetic.main.marvel_characters_layout.*


class MarvelCharactersFragment :
    BaseFragment<MarvelCharactersViewModel, MarvelCharactersLayoutBinding, MarvelCharactersRepository>() {

    var currentPage: Int = 1
    var lastPage: Int = -1


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        Glide.with(requireContext()).load(R.drawable.search_icon).into(binding.searchButton)


        //To set loading style of load more and refresh
        binding.MarvelCharactersListRVID.setLoadingMoreProgressStyle(3)
        binding.MarvelCharactersListRVID.setRefreshProgressStyle(3)
        //Recyclerview load more code
        binding.MarvelCharactersListRVID.setLoadingListener(object : LoadingListener {
            override fun onRefresh() {
                currentPage = 1
                lastPage = -1
                updateMarvelList()
                binding.MarvelCharactersListRVID.setNoMore(false)
                binding.MarvelCharactersListRVID.setLoadingMoreEnabled(true)
            }

            override fun onLoadMore() {
                if (currentPage <= lastPage) {
                    updateMarvelList()
                } else {
                    binding.MarvelCharactersListRVID.setNoMore(false)
                }
            }
        })

        search_button.setOnClickListener {
            replace(SearchFragment.newInstance())
        }

        //Initialize the RV
        updateMarvelList()
    }

   private fun replace(fragment: Fragment) {
        (activity as MainActivity).replaceFragment(fragment,R.id.rlParent,
            inAnimRes = R.anim.slide_in_right.takeIf { true } ?: R.anim.slide_in_left,
            outAnimRes = R.anim.slide_out_left.takeIf { true }
                ?: R.anim.slide_out_right)
    }


    private fun updateMarvelList(/*list: ArrayList<ListUpdatesDataArrayModel>*/) {
        var adapter: CharactersAdapter? = null
        if (currentPage == 1) {

            MarvelCharactersListRVID.apply {
                adapter = CharactersAdapter(activity?.applicationContext!!/*, list*/)
                MarvelCharactersListRVID.adapter = adapter
            }

        } else {

        }

        adapter?.notifyDataSetChanged()
        MarvelCharactersListRVID.loadMoreComplete()
        MarvelCharactersListRVID.refreshComplete()

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