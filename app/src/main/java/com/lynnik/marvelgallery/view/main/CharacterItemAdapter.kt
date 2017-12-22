package com.lynnik.marvelgallery.view.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.lynnik.marvelgallery.R
import com.lynnik.marvelgallery.model.MarvelCharacter
import com.lynnik.marvelgallery.view.common.ItemAdapter
import com.lynnik.marvelgallery.view.common.bindView
import com.lynnik.marvelgallery.view.common.loadImage

class CharacterItemAdapter(
    val character: MarvelCharacter,
    val clicked: (MarvelCharacter) -> Unit
) : ItemAdapter<CharacterItemAdapter.ViewHolder>(R.layout.item_character) {

  override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

  override fun ViewHolder.onBindViewHolder() {
    textView.text = character.name
    imageView.loadImage(character.imageUrl)
    itemView.setOnClickListener { clicked(character) }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView by bindView(R.id.textView)
    val imageView: ImageView by bindView(R.id.imageView)
  }
}