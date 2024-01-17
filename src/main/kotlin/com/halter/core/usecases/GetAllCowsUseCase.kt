package com.halter.core.usecases

import com.halter.core.domain.Cow
import com.halter.core.repositories.CowRepository
import com.halter.core.services.DeviceService
import jakarta.inject.Singleton

interface GetAllCowsUseCase {
  fun execute(): Result<List<Cow>>

}

@Singleton
class GetAllCowsUseCaseImpl(
  private val cowRepository: CowRepository,
  private val deviceService: DeviceService
) : GetAllCowsUseCase {
  override fun execute(): Result<List<Cow>> {
    val cows = cowRepository.findAll().getOrThrow()
    try {
      val cowsWithDevice = cows.map { cow ->
        val device = deviceService.getDeviceByCollarId(cow.collarId)
        cow.copy(
          collarStatus = device.status,
          lastLocation = device.lastLocation
        )
      }

      return Result.success(cowsWithDevice)
    } catch (e: Exception) {
      return Result.failure(e)
    }
  }
}