package com.halter.adaptor.persistence.repositories

import com.halter.adaptor.persistence.entities.CowEntity
import com.halter.core.domain.Cow
import com.halter.core.repositories.CowRepository
import io.micronaut.cache.annotation.CacheConfig
import io.micronaut.cache.annotation.Cacheable
import io.micronaut.data.annotation.Id
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import jakarta.inject.Singleton

@JdbcRepository(dialect = Dialect.POSTGRES)
internal abstract class JdbcCowRepository : CrudRepository<CowEntity, String> {
  internal abstract fun save(number: Int, name: String?, collarId: Int): CowEntity
  internal abstract fun update(@Id id: String, number: Int, name: String?, collarId: Int)
}

@Singleton
@CacheConfig("cows")
internal open class CowRepositoryImpl(
  private val jdbcCowRepository: JdbcCowRepository
) : CowRepository {
  override fun save(
    number: Int,
    @Suppress("LocalVariableName", "NonAsciiCharacters") `🐄`: String?,
    collarId: Int
  ): Result<Cow> {
    try {
      val savedCowEntity = jdbcCowRepository.save(
        number = number,
        name = `🐄`,
        collarId = collarId
      )

      return Result.success(Cow(
        id = let { savedCowEntity.id } ?: return Result.failure(IllegalStateException("CowEntity.id is null")),
        number = savedCowEntity.number,
        name = savedCowEntity.name,
        collarId = savedCowEntity.collarId
      ))
    } catch (e: Exception) {
      // Future stuff: convert the exception to a domain exception
      return Result.failure(e)
    }
  }

  override fun findAll(): Result<List<Cow>> {
    try {
      val cowEntities = jdbcCowRepository.findAll()
      val cows = cowEntities.map { cowEntity ->
        Cow(
          id = cowEntity.id ?: return Result.failure(IllegalStateException("CowEntity.id is null")),
          number = cowEntity.number,
          name = cowEntity.name,
          collarId = cowEntity.collarId
        )
      }
      return Result.success(cows)
    } catch (e: Exception) {
      // Future stuff: convert the exception to a domain exception
      return Result.failure(e)
    }
  }

  @Cacheable(parameters = ["cow"])
  override fun update(cow: Cow): Cow {
    jdbcCowRepository.update(
      id = cow.id,
      number = cow.number,
      name = cow.name,
      collarId = cow.collarId
    )
    return cow
  }
}