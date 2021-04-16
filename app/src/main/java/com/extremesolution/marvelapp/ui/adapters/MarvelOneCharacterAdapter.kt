package com.extremesolution.marvelapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.extremesolution.marvelapp.R
import kotlinx.android.synthetic.main.marvel_type_cell_for_adapter_layout.view.*

class MarvelOneCharacterAdapter(var context: Context) :
    RecyclerView.Adapter<MarvelOneCharacterAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.marvel_type_cell_for_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(R.drawable.mcu_background)
            .placeholder(R.drawable.image_placeholder).into(holder.itemView.CategoryImgID)


    }

    override fun getItemCount(): Int {
        return 7
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}