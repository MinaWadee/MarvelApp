package com.extremesolution.marvelapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.data.models.StoriesModels.StoriesResult
import kotlinx.android.synthetic.main.marvel_type_cell_for_adapter_layout.view.*

class MarvelStoriesAdapter(var context: Context, val list: List<StoriesResult>) :
    RecyclerView.Adapter<MarvelStoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.marvel_type_cell_for_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        Glide.with(context)
            .load(list[position].thumbnail?.path + "." + list[position].thumbnail?.extension)
            .placeholder(R.drawable.image_placeholder).into(holder.itemView.CategoryImgID)

        holder.itemView.MarvelCharacterNameAndDesTV.text = list[position].title

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}