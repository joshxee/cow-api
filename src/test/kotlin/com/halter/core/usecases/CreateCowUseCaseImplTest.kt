package com.halter.core.usecases

import com.halter.core.arguments.CreateCowArgument
import com.halter.core.domain.Cow
import com.halter.core.domain.Device
import com.halter.core.domain.LastLocation
import com.halter.core.repositories.CowRepository
import com.halter.core.services.DeviceService
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mockito.mock
import kotlin.random.Random.Default.nextInt

class CreateCowUseCaseImplTest {

  private val cowRepository = mock<CowRepository>()
  private val deviceService = mock<DeviceService>()
  private val tested = CreateCowUseCaseImpl(cowRepository, deviceService)

  @Test
  fun `should return cow when execute`() {
    val createCowRequest = mock<CreateCowArgument>()
    val number = nextInt()
    val collarId = nextInt()
    val name = randomAlphabetic(12)
    val cow = Cow(
      id = randomAlphabetic(36),
      number = number,
      `🐄` = name,
      collarId = collarId
    )
    val cowResult = Result.success(cow)
    val deviceStatus = randomAlphabetic(12)
    val lastLocation = mock<LastLocation>()

    val device = Device(
      status = deviceStatus,
      lastLocation = lastLocation
    )
    val deviceResult = Result.success(device)

    val expected = cow.copy(
      collarStatus = deviceStatus,
      lastLocation = lastLocation
    )

    // Given
    given(createCowRequest.number).willReturn(number)
    given(createCowRequest.collarId).willReturn(collarId)
    given(createCowRequest.`🐄`).willReturn(name)

    given(cowRepository.save(number, name, collarId)).willReturn(cowResult)
    given(deviceService.getDeviceByCollarId(collarId)).willReturn(deviceResult)

    // When
    val actual = tested.execute(createCowRequest)

    // Then
    then(cowRepository).should().save(number, name, collarId)
    then(deviceService).should().getDeviceByCollarId(collarId)

    assert(actual.isSuccess)
    assertEquals(expected, actual.getOrNull())
  }
}