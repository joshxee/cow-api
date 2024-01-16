package com.halter.core.arguments

data class UpdateCowArgument(
  val id: String,
  val number: Int,
  val collarId: Int,
  @Suppress("NonAsciiCharacters", "PropertyName") val `🐄`: String? = null,
)