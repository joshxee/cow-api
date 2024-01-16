package com.halter.adaptor.dtos

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CowRequest (
  val number: Int,
  @JsonProperty("collar_id") val collarId: Int,
  @Suppress("NonAsciiCharacters", "PropertyName") @JsonProperty("🐄") val `🐄`: String? = null,
)
