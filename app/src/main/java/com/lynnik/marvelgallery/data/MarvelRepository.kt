package com.lynnik.marvelgallery.data

import com.lynnik.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

interface MarvelRepository {

  fun getAllCharacters(): Single<List<MarvelCharacter>>

  companion object : Provider<MarvelRepository>() {

    override fun creator() = MarvelRepositoryImpl()
  }
}