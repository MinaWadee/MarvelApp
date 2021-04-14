package com.extremesolution.marvelapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.extremesolution.marvelapp.R

class CharactersAdapter(var context: Context) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_list_for_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       // holder.itemView.MarvelCharacterNameTV.text = "add"

    }

    override fun getItemCount(): Int {
        return 4
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

}