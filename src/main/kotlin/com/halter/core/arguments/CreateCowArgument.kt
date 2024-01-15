package com.halter.core.arguments

data class CreateCowArgument(
  val number: Number,
  val collarId: Number,
  @Suppress("NonAsciiCharacters", "PropertyName") val `🐄`: String? = null,
)