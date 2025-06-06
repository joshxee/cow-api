package com.halter.core.repositories

import com.halter.core.domain.Cow

interface CowRepository {
  fun save(number: Int, @Suppress("LocalVariableName", "NonAsciiCharacters") `🐄`: String?, collarId: Int): Result<Cow>
  fun findAll(): Result<List<Cow>>
  fun update(cow: Cow): Cow
}