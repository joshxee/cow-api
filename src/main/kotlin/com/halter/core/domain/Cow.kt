package com.halter.core.domain

data class Cow(
  val id: String,
  val number: Number,
  val collarId: Number,
  @Suppress("NonAsciiCharacters", "PropertyName") val `🐄`: String? = null,
)
