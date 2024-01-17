package com.halter.adaptor.persistence

import com.halter.core.arguments.UpdateCowArgument
import com.halter.core.domain.Cow
import com.halter.core.domain.Device
import com.halter.core.repositories.CowRepository
import com.halter.core.services.DeviceService
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mockito.mock
import kotlin.random.Random.Default.nextInt

class UpdateCowUseCaseImplTest {
  private val deviceService: DeviceService = mock()
  private val cowRepository: CowRepository = mock()
  private val tested = UpdateCowUseCaseImpl(deviceService, cowRepository)

  @Test
  fun `should update cow when execute`() {
    val number = nextInt(0, 1000)
    val collarId = nextInt(0, 1000)
    val id = RandomStringUtils.randomAlphanumeric(36)
    val existingCow = Cow(
      id = id,
      number = number,
      collarId = collarId
    )
    val updatedCow = Cow(
      id = id,
      number = number,
      collarId = collarId
    )

    val arguments = UpdateCowArgument(
      number = number,
      collarId = collarId,
      id = id
    )

    val device = Device(
      status = RandomStringUtils.randomAlphabetic(10),
      lastLocation = mock()
    )

    // Given
    given(cowRepository.update(existingCow)).willReturn(updatedCow)
    given(deviceService.getDeviceByCollarId(collarId)).willReturn(device)

    // When
    val actual = tested.execute(arguments).getOrNull()

    // Then
    then(cowRepository).should().update(existingCow)
    then(deviceService).should().getDeviceByCollarId(collarId)

    val expected = updatedCow.copy(
      collarStatus = device.status,
      lastLocation = device.lastLocation
    )

    assertNotNull(actual)
    assertEquals(expected, actual)
  }
}