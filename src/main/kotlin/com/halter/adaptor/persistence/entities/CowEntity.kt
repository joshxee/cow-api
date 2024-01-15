package com.halter.adaptor.persistence.entities

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@MappedEntity(value = "cow")
data class CowEntity(
  @Id
  @GeneratedValue(GeneratedValue.Type.UUID)
  val id: String? = null,

  val number: Number,

  val name: String? = null,

  val collarId: Number
)
