package com.extremesolution.marvelapp.ui.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.appActivities.MainActivity
import com.extremesolution.marvelapp.data.models.CharacterModel.Result
import com.extremesolution.marvelapp.general.RSBlurProcessor
import com.extremesolution.marvelapp.ui.home.MarvelCharacterDetailsFragment
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.character_list_for_adapter_layout.view.*


class CharactersAdapter(var context: Context, val list: List<Result>, val activity: MainActivity) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    val blurImage: RSBlurProcessor? = null

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_list_for_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        //To make bg image blur
        Glide.with(context)
            .load(list[position].thumbnail.path + "." + list[position].thumbnail.extension)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(holder.itemView.MarvelTextBG)

        Glide.with(context)
            .load(list[position].thumbnail.path + "." + list[position].thumbnail.extension)
            .placeholder(R.drawable.image_placeholder).into(holder.itemView.MarvelCharacterImage)

        holder.itemView.MarvelCharacterNameTV.text = list[position].name

        holder.itemView.setOnClickListener {
            val bundle = Bundle()

            bundle.putString("Description", list[position].description)
            bundle.putString("CharacterID", list[position].id.toString().trim())
            bundle.putString("CharacterName", list[position].name)
            bundle.putString(
                "CharacterImg",
                list[position].thumbnail.path + "." + list[position].thumbnail.extension
            )
            replace(MarvelCharacterDetailsFragment.newInstance(), bundle)
        }

    }

    private fun replace(fragment: Fragment, args: Bundle) {
        activity.replaceFragment(fragment, R.id.rlParent,
            sendingData = true, bundle = args, needToAddToBackStack = true,
            inAnimRes = R.anim.slide_in_right.takeIf { true } ?: R.anim.slide_in_left,
            outAnimRes = R.anim.slide_out_left.takeIf { true } ?: R.anim.slide_out_right)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}