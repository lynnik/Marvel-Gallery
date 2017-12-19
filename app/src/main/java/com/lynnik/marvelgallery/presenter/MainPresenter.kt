package com.lynnik.marvelgallery.presenter

import com.lynnik.marvelgallery.data.MarvelRepository
import com.lynnik.marvelgallery.data.applySchedulers
import com.lynnik.marvelgallery.data.plusAssign
import com.lynnik.marvelgallery.data.subscribeBy
import com.lynnik.marvelgallery.view.main.MainView

class MainPresenter(val view: MainView, val repository: MarvelRepository) : BasePresenter() {

  fun onViewCreated() {
    loadCharacters()
  }

  fun onRefresh() {
    loadCharacters()
  }

  private fun loadCharacters() {
    repository.getAllCharacters()
    subscriptions += repository.getAllCharacters()
        .applySchedulers()
        .doOnSubscribe { view.refresh = true }
        .doFinally { view.refresh = false }
        .subscribeBy(onSuccess = view::show, onError = view::showError)
  }
}