package com.lynnik.marvelgallery.helpers

import com.lynnik.marvelgallery.data.MarvelRepository
import com.lynnik.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

class BaseMarvelRepository(val onGetCharacters: (String?) -> Single<List<MarvelCharacter>>)
  : MarvelRepository {

  override fun getAllCharacters(searchQuery: String?) = onGetCharacters(searchQuery)
}