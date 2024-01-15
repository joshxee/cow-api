package com.halter.adaptor.persistence.repositories

import com.halter.adaptor.persistence.entities.CowEntity
import com.halter.core.domain.Cow
import com.halter.core.repositories.CowRepository
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.GenericRepository
import jakarta.inject.Singleton

@JdbcRepository(dialect = Dialect.POSTGRES)
internal abstract class JdbcCowRepository : GenericRepository<CowEntity, String> {
  internal abstract fun save(number: Number, name: String?, collarId: Number): CowEntity
}

@Singleton
internal class CowRepositoryImpl(
  private val jdbcCowRepository: JdbcCowRepository
) : CowRepository {
  override fun save(number: Number, @Suppress("LocalVariableName", "NonAsciiCharacters") `🐄`: String?, collarId: Number): Result<Cow> {
    val savedCowEntity = jdbcCowRepository.save(
      number = number,
      name = `🐄`,
      collarId = collarId
    )

    return Result.success(Cow(
      id = let { savedCowEntity.id } ?: return Result.failure(IllegalStateException("CowEntity.id is null")),
      number = savedCowEntity.number,
      `🐄` = savedCowEntity.name,
      collarId = savedCowEntity.collarId
    ))
  }
}