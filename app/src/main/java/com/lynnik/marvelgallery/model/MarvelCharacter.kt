package com.lynnik.marvelgallery.model

import com.lynnik.marvelgallery.data.network.dto.CharacterMarvelDto

class MarvelCharacter(
    val name: String,
    val imageUrl: String) {

  constructor(dto: CharacterMarvelDto) : this(
      name = dto.name,
      imageUrl = dto.imageUrl
  )
}