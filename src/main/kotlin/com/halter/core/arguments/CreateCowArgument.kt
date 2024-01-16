package com.halter.core.arguments

data class CreateCowArgument(
  val number: Int,
  val collarId: Int,
  @Suppress("NonAsciiCharacters", "PropertyName") val `🐄`: String? = null,
)