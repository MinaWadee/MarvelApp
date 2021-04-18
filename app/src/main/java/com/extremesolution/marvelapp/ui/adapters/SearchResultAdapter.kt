package com.extremesolution.marvelapp.ui.adapters

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.data.models.CharacterModel.CharacterResult
import kotlinx.android.synthetic.main.search_result_for_adapter_layout.view.*
import java.text.Normalizer
import kotlin.math.min


class SearchResultAdapter(
    var context: Context,
    var list: ArrayList<CharacterResult>,
    var writtenCharacters: String
) :
    RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_for_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(list[position].thumbnail.path + "." + list[position].thumbnail.extension)
            .into(holder.itemView.CharacterImg)


        if (writtenCharacters != "") {
        holder.itemView.CharacterNameTV.text =  highlight(writtenCharacters, list[position].name)
        }else{
        holder.itemView.CharacterNameTV.text = list[position].name
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun highlight(search: String, originalText: String): CharSequence? {
        val normalizedText =
            Normalizer.normalize(originalText, Normalizer.Form.NFD)
                ?.replace("\\p{InCombiningDiacriticalMarks}+", "")
        var start = normalizedText?.indexOf(search)
        return if (start!! == -1 ) {
            originalText
        } else {
            val highlighted: Spannable = SpannableString(originalText)
            while (start!! >= 0) {
                val spanStart = min(start, originalText.length)
                val spanEnd = (start + search.length).coerceAtMost(originalText.length)
                highlighted.setSpan(
                    BackgroundColorSpan(Color.RED),
                    spanStart,
                    spanEnd,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                start = normalizedText?.indexOf(search, spanEnd)
            }
            highlighted
        }
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}