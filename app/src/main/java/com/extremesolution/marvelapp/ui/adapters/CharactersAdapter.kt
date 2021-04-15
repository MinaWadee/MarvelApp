package com.extremesolution.marvelapp.ui.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.extremesolution.marvelapp.R
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.character_list_for_adapter_layout.view.*


class CharactersAdapter(var context: Context) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_list_for_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       // holder.itemView.MarvelCharacterNameTV.text = "add"

        //To make bg image blur
        Glide.with(context).load(R.drawable.mcu_background)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(holder.itemView.MarvelTextBG)

    }

    override fun getItemCount(): Int {
        return 4
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}