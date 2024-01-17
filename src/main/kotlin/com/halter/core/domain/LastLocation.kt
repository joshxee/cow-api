package com.halter.core.domain

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class LastLocation (
  val latitude: Double,
  val longitude: Double,
)
