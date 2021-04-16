package com.extremesolution.marvelapp.ui.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.general.RSBlurProcessor
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.character_list_for_adapter_layout.view.*


class CharactersAdapter(var context: Context) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    val blurImage: RSBlurProcessor? = null

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_list_for_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        //To make bg image blur
        Glide.with(context).load(R.drawable.solid_black)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(holder.itemView.MarvelTextBG)

        holder.itemView.setOnClickListener {
            it.findNavController().navigate(R.id.action_MarvelCharacter_to_MarvelCharacterDetails)
        }

    }

    override fun getItemCount(): Int {
        return 8
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}