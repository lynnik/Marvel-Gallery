package com.lynnik.marvelgallery.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.lynnik.marvelgallery.data.network.dto.CharacterMarvelDto
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class MarvelCharacter(
    val name: String,
    val imageUrl: String
) : Parcelable {

  constructor(dto: CharacterMarvelDto) : this(
      name = dto.name,
      imageUrl = dto.imageUrl
  )
}