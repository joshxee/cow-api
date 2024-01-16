package com.halter.core.usecases

import com.halter.core.arguments.CreateCowArgument
import com.halter.core.domain.Cow
import com.halter.core.repositories.CowRepository
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mockito.mock
import kotlin.random.Random.Default.nextInt

class CreateCowUseCaseImplTest{

  private val cowRepository: CowRepository = mock()
  private val tested = CreateCowUseCaseImpl(cowRepository)

  @Test
  fun `should return cow when execute`() {
    val createCowRequest = mock<CreateCowArgument>()
    val number = nextInt()
    val collarId = nextInt()
    val name = randomAlphabetic(12)
    val cow = mock<Cow>()
    val cowResult = Result.success(cow)

    // Given
    given(createCowRequest.number).willReturn(number)
    given(createCowRequest.collarId).willReturn(collarId)
    given(createCowRequest.`🐄`).willReturn(name)

    given(cowRepository.save(number, name, collarId)).willReturn(cowResult)

    // When
    val actual = tested.execute(createCowRequest)

    // Then
    then(cowRepository).should().save(number, name, collarId)

    assert(actual.isSuccess)
    assertEquals(cow, actual.getOrNull())
  }
}