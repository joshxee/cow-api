package com.halter.adaptor.dtos

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CowResponse(
  val id: String,
  val cowNumber: String,
  val collarId: String,
  @Suppress("PropertyName", "NonAsciiCharacters") val `🐄`: String?,
  val collarStatus: String?,
  val lastLocation: LastLocationResponse?,
)

@Serdeable
data class LastLocationResponse(
  val latitude: Double,
  val longitude: Double,
)
