package com.halter.adaptor.controllers

import com.halter.adaptor.dtos.CreateCowRequest
import com.halter.core.arguments.CreateCowArgument
import com.halter.core.domain.Cow
import com.halter.core.usecases.CreateCowUseCase
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mockito.mock
import kotlin.random.Random.Default.nextInt

class CowsControllerTest {

  private val createCowUseCase = mock<CreateCowUseCase>()
  private val tested = CowsController(createCowUseCase)

  @Test
  fun `should create cow`() {
    val number = nextInt()
    val collarId = nextInt()
    val createCowRequest = CreateCowRequest(
      number = number,
      collarId = collarId
    )

    val arguments = CreateCowArgument(
      number = number,
      collarId = collarId
    )

    val cow = mock<Cow>()
    val cowResult = Result.success(cow)
    val id = RandomStringUtils.randomAlphanumeric(36)

    // Given
    given(createCowUseCase.execute(arguments)).willReturn(cowResult)
    given(cow.id).willReturn(id)
    given(cow.number).willReturn(number)
    given(cow.collarId).willReturn(collarId)
    given(cow.`🐄`).willReturn(null)

    // When
    val actual = tested.create(createCowRequest)

    // Then
    then(createCowUseCase).should().execute(arguments)
    then(cow).should().id
    then(cow).should().number
    then(cow).should().collarId
    then(cow).should().`🐄`

    assertEquals(actual.id, id)
    assertEquals(actual.number, number)
    assertEquals(actual.collarId, collarId)
    assertEquals(actual.`🐄`, null)
  }

  @Test
  fun `should throw exception when create cow use case returns failure`() {
    val number = nextInt()
    val collarId = nextInt()
    val createCowRequest = CreateCowRequest(
      number = number,
      collarId = collarId
    )

    val arguments = CreateCowArgument(
      number = number,
      collarId = collarId
    )

    val cowResult = Result.failure<Cow>(Exception("Unexpected result"))

    // Given
    given(createCowUseCase.execute(arguments)).willReturn(cowResult)

    // When
    val actual = kotlin.runCatching { tested.create(createCowRequest) }

    // Then
    then(createCowUseCase).should().execute(arguments)

    assertEquals(actual.exceptionOrNull()!!.message, "Unexpected result")
  }
}