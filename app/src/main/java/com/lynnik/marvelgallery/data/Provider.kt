package com.lynnik.marvelgallery.data

abstract class Provider<T> {

  abstract fun creator(): T
  var testingInstance: T? = null
  private val instance: T by lazy { creator() }

  fun get(): T = testingInstance ?: instance
}