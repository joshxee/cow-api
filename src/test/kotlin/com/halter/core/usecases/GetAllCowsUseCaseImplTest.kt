package com.halter.core.usecases

import com.halter.core.domain.Cow
import com.halter.core.domain.Device
import com.halter.core.repositories.CowRepository
import com.halter.core.services.DeviceService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mockito.mock
import kotlin.random.Random.Default.nextInt

class GetAllCowsUseCaseImplTest {
  private val deviceService = mock<DeviceService>()
  private val cowRepository = mock<CowRepository>()
  private val tested = GetAllCowsUseCaseImpl(cowRepository, deviceService)

  @Test
  fun `should get all cows`() {
    val cow = mock<Cow>()
    val collarId = nextInt()
    val device = mock<Device>()

    // Given
    given(cowRepository.findAll()).willReturn(Result.success(listOf(cow)))
    given(cow.collarId).willReturn(collarId)
    given(deviceService.getDeviceByCollarId(collarId)).willReturn(device)

    // When
    val actual = tested.execute()

    // Then
    then(cowRepository).should().findAll()
    then(cow).should().collarId
    then(deviceService).should().getDeviceByCollarId(collarId)

    assertEquals(actual.getOrNull()?.size, 1)
    val cowWithDevice = actual.getOrNull()?.first()
    assertEquals(cowWithDevice?.collarStatus, device.status)
    assertEquals(cowWithDevice?.lastLocation, device.lastLocation)
  }
}