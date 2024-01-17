package com.halter.core.domain

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Cow(
  val id: String,
  val number: Int,
  val collarId: Int,
  val name: String? = null,
  val collarStatus: String? = null,
  val lastLocation: LastLocation? = null,
)
