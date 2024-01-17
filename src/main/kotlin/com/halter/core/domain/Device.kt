package com.halter.core.domain

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Device (
  val status: String,
  val lastLocation: LastLocation
)