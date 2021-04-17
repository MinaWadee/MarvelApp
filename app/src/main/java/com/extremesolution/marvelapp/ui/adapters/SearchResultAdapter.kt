package com.extremesolution.marvelapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.extremesolution.marvelapp.R
import kotlinx.android.synthetic.main.marver_type_for_adapter_layout.view.*

class SearchResultAdapter (var context: Context) :
    RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_for_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 4
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}