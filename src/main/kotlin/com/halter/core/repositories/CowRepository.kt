package com.halter.core.repositories

import com.halter.core.domain.Cow

interface CowRepository {
  fun save(number: Number, @Suppress("LocalVariableName", "NonAsciiCharacters") `🐄`: String?, collarId: Number): Result<Cow>
}