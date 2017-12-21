package com.lynnik.marvelgallery

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.lynnik.marvelgallery.data.MarvelRepository
import com.lynnik.marvelgallery.model.MarvelCharacter
import com.lynnik.marvelgallery.presenter.MainPresenter
import com.lynnik.marvelgallery.view.common.BaseActivityWithPresenter
import com.lynnik.marvelgallery.view.common.addOnTextChangedListener
import com.lynnik.marvelgallery.view.common.bindToSwipeRefresh
import com.lynnik.marvelgallery.view.common.toast
import com.lynnik.marvelgallery.view.main.CharacterItemAdapter
import com.lynnik.marvelgallery.view.main.MainListAdapter
import com.lynnik.marvelgallery.view.main.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivityWithPresenter(), MainView {

  override var refresh by bindToSwipeRefresh(R.id.swipeRefreshView)
  override val presenter by lazy { MainPresenter(this, MarvelRepository.get()) }

  private val characters = listOf(
      MarvelCharacter(name = "3-D Man", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c"),
      MarvelCharacter(name = "Abomination (Emil Blonsky)", imageUrl = "http://i.annihil.us/u")
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    requestWindowFeature(Window.FEATURE_NO_TITLE)
    setContentView(R.layout.activity_main)

    recyclerView.layoutManager = GridLayoutManager(this, 2)
    swipeRefreshView.setOnRefreshListener { presenter.onRefresh() }
    searchView.addOnTextChangedListener {
      onTextChanged { text, _, _, _ ->
        presenter.onSearchChanged(text)
      }
    }
    presenter.onViewCreated()
  }

  override fun show(items: List<MarvelCharacter>) {
    val categoryItemAdapters = items.map(::CharacterItemAdapter)
    recyclerView.adapter = MainListAdapter(categoryItemAdapters)
  }

  override fun showError(error: Throwable) {
    toast("Error: ${error.message}")
    error.printStackTrace()
  }
}