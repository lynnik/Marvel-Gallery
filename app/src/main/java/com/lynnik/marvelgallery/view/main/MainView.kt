package com.lynnik.marvelgallery.view.main

import com.lynnik.marvelgallery.model.MarvelCharacter

interface MainView {

  var refresh: Boolean

  fun show(items: List<MarvelCharacter>)

  fun showError(error: Throwable)
}