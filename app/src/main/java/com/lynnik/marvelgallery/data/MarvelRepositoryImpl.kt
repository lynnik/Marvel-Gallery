package com.lynnik.marvelgallery.data

import com.lynnik.marvelgallery.data.network.MarvelApi
import com.lynnik.marvelgallery.data.network.provider.retrofit
import com.lynnik.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

class MarvelRepositoryImpl : MarvelRepository {

  val api = retrofit.create(MarvelApi::class.java)

  override fun getAllCharacters(): Single<List<MarvelCharacter>> =
      api.getCharacters(offset = 0, limit = elementsOnListLimit)
          .map { it.data?.results.orEmpty().map(::MarvelCharacter) }

  companion object {
    const val elementsOnListLimit = 50
  }
}