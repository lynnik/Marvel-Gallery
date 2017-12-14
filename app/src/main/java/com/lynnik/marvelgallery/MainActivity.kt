package com.lynnik.marvelgallery

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.lynnik.marvelgallery.model.MarvelCharacter
import com.lynnik.marvelgallery.view.main.CharacterItemAdapter
import com.lynnik.marvelgallery.view.main.MainListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private val characters = listOf(
      MarvelCharacter(name = "3-D Man", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c"),
      MarvelCharacter(name = "Abomination (Emil Blonsky)", imageUrl = "http://i.annihil.us/u")
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    requestWindowFeature(Window.FEATURE_NO_TITLE)
    setContentView(R.layout.activity_main)

    recyclerView.layoutManager = GridLayoutManager(this, 2)
    val categoryItemAdapters = characters.map(::CharacterItemAdapter)
    recyclerView.adapter = MainListAdapter(categoryItemAdapters)
  }
}