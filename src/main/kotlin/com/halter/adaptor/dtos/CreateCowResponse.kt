package com.halter.adaptor.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateCowResponse(
  val id: String,
  val number: Number,
  @JsonProperty("collar_id")
  val collarId: Number,
  @Suppress("NonAsciiCharacters", "PropertyName") val `🐄`: String? = null,
)
