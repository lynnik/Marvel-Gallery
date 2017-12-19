package com.lynnik.marvelgallery.helpers

import com.lynnik.marvelgallery.data.MarvelRepository
import io.reactivex.Single

class BaseMarvelRepository(val onGetCharacters: () -> Single<List<MarvelCharacter>>)
  : MarvelRepository {

  override fun getAllCharacters() = onGetCharacters()
}