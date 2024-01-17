package com.halter.adaptor.persistence.repositories

import com.halter.adaptor.persistence.entities.CowEntity
import com.halter.core.domain.Cow
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mockito.mock
import kotlin.random.Random.Default.nextInt

class CowRepositoryImplTest {

  private val jdbcCowRepository = mock<JdbcCowRepository>()
  private val tested = CowRepositoryImpl(jdbcCowRepository)

  @Test
  fun `should persist cow entity when save`() {
    val number = nextInt()
    val collarId = nextInt()
    val id = RandomStringUtils.randomAlphanumeric(36)
    val cowEntity = CowEntity(id = id, number = number, name = null, collarId = collarId)
    val expected = Cow(id = id, number = number, name = null, collarId = collarId)

    // Given
    given(
      jdbcCowRepository.save(number = number, name = null, collarId = collarId)
    ).willReturn(
      cowEntity
    )

    // When
    val actual = tested.save(
      number = number,
      `🐄` = null,
      collarId = collarId
    )

    // Then
    then(jdbcCowRepository).should().save(number = number, name = null, collarId = collarId)

    // And
    Assertions.assertTrue(actual.isSuccess)
    Assertions.assertEquals(expected, actual.getOrNull())
  }

  @Test
  fun `should return failure when save and jdbc repository throws an exception`() {
    val number = nextInt()
    val collarId = nextInt()
    val expected = RuntimeException("Something went wrong")

    // Given
    given(
      jdbcCowRepository.save(number = number, name = null, collarId = collarId)
    ).willThrow(
      expected
    )

    // When
    val actual = tested.save(
      number = number,
      `🐄` = null,
      collarId = collarId
    )

    // Then
    then(jdbcCowRepository).should().save(number = number, name = null, collarId = collarId)

    // And
    Assertions.assertTrue(actual.isFailure)
    Assertions.assertEquals(expected, actual.exceptionOrNull())
  }
}