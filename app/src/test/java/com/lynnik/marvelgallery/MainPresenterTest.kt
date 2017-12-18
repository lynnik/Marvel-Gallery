package com.lynnik.marvelgallery

import com.lynnik.marvelgallery.data.MarvelRepository
import com.lynnik.marvelgallery.model.MarvelCharacter
import com.lynnik.marvelgallery.presenter.MainPresenter
import com.lynnik.marvelgallery.view.main.MainView
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail
import org.junit.Test

@Suppress("IllegalIdentifier")
class MainPresenterTest {

  @Test
  fun `After view was created, list of characters is loaded and displayed`() {
    assertOnAction { onViewCreated() }.thereIsSameListDisplayed()
  }

  private fun assertOnAction(action: MainPresenter.() -> Unit)
      = PresenterActionAssertion(action)

  private class PresenterActionAssertion(val actionOnPresenter: MainPresenter.() -> Unit) {

    fun thereIsSameListDisplayed() {
      val exampleCharacterList = listOf(
          MarvelCharacter("ExampleName", "ExampleImageUrl"),
          MarvelCharacter("Name1", "ImageUrl1"),
          MarvelCharacter("Name2", "ImageUrl2")
      )

      var displayedList: List<MarvelCharacter>? = null

      val view = object : MainView {

        override var refresh: Boolean = false

        override fun show(items: List<MarvelCharacter>) {
          displayedList = items
        }

        override fun showError(error: Throwable) {
          fail()
        }
      }

      val marvelRepository = object : MarvelRepository {

        override fun getAllCharacters(): Single<List<MarvelCharacter>> =
            Single.just(exampleCharacterList)
      }

      val mainPresenter = MainPresenter(view, marvelRepository)

      mainPresenter.actionOnPresenter()

      assertEquals(exampleCharacterList, displayedList)
    }
  }
}