package com.halter.adaptor.dtos

data class CowResponse(
  val id: String,
  val cowNumber: String,
  val collarId: String,
  @Suppress("PropertyName", "NonAsciiCharacters") val `🐄`: String?,
  val collarStatus: String?,
  val lastLocation: LastLocationResponse?,
)

data class LastLocationResponse(
  val latitude: Double,
  val longitude: Double,
)
