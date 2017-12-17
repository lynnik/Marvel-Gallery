package com.lynnik.marvelgallery.data.network.provider

import com.lynnik.marvelgallery.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

fun makeLoggingInterceptor() = HttpLoggingInterceptor().apply {
  level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
  else HttpLoggingInterceptor.Level.NONE
}