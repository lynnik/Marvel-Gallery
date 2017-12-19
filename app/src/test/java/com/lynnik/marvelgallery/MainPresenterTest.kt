package com.lynnik.marvelgallery

import com.lynnik.marvelgallery.helpers.BaseMainView
import com.lynnik.marvelgallery.helpers.BaseMarvelRepository
import com.lynnik.marvelgallery.helpers.Example
import com.lynnik.marvelgallery.model.MarvelCharacter
import com.lynnik.marvelgallery.presenter.MainPresenter
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test

@Suppress("IllegalIdentifier")
class MainPresenterTest {

  @Before
  fun setUp() {
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
  }

  @Test
  fun `After view was created, list of characters is loaded and displayed`() {
    assertOnAction { onViewCreated() }.thereIsSameListDisplayed()
  }

  @Test
  fun `New list is shown after view was refreshed`() {
    assertOnAction { onRefresh() }.thereIsSameListDisplayed()
  }

  @Test
  fun `When API returns error, it is displayed on view`() {
    val someError = Error()

    var errorDisplayed: Throwable? = null

    val view = BaseMainView(
        onShow = { _ -> fail() },
        onShowError = { errorDisplayed = it }
    )

    val marvelRepository = BaseMarvelRepository { Single.error(someError) }

    val mainPresenter = MainPresenter(view, marvelRepository)
    mainPresenter.onViewCreated()

    assertEquals(someError, errorDisplayed)
  }

  @Test
  fun `When presenter is waiting for response, refresh is displayed`() {
    val view = BaseMainView(refresh = false)

    val marvelRepository = BaseMarvelRepository(
        onGetCharacters = {
          Single.fromCallable {
            assertTrue(view.refresh)
            Example.exampleCharacterList
          }
        }
    )

    val mainPresenter = MainPresenter(view, marvelRepository)
    view.onShow = { _ -> assertTrue(view.refresh) }
    mainPresenter.onViewCreated()

    assertFalse(view.refresh)
  }

  private fun assertOnAction(action: MainPresenter.() -> Unit)
      = PresenterActionAssertion(action)

  private class PresenterActionAssertion(val actionOnPresenter: MainPresenter.() -> Unit) {

    fun thereIsSameListDisplayed() {
      var displayedList: List<MarvelCharacter>? = null

      val view = BaseMainView(
          onShow = { items -> displayedList = items },
          onShowError = { fail() }
      )

      val marvelRepository = BaseMarvelRepository(
          onGetCharacters = { Single.just(Example.exampleCharacterList) }
      )

      val mainPresenter = MainPresenter(view, marvelRepository)
      mainPresenter.actionOnPresenter()

      assertEquals(Example.exampleCharacterList, displayedList)
    }
  }
}