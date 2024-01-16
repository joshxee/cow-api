package com.halter.core.domain

data class Cow(
  val id: String,
  val number: Int,
  val collarId: Int,
  @Suppress("NonAsciiCharacters", "PropertyName") val `🐄`: String? = null,
  val collarStatus: String? = null,
  val lastLocation: LastLocation? = null,
)
