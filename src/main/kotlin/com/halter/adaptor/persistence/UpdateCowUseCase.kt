package com.halter.adaptor.persistence

import com.halter.core.arguments.UpdateCowArgument
import com.halter.core.domain.Cow
import com.halter.core.repositories.CowRepository
import com.halter.core.services.DeviceService
import jakarta.inject.Singleton

interface UpdateCowUseCase {
  fun execute(arguments: UpdateCowArgument): Result<Cow>

}

@Singleton
class UpdateCowUseCaseImpl(
  private val deviceService: DeviceService,
  private val cowRepository: CowRepository
) : UpdateCowUseCase {
  override fun execute(arguments: UpdateCowArgument): Result<Cow> {
    try {
      val cow = cowRepository.update(
        Cow(
          id = arguments.id,
          number = arguments.number,
          name = arguments.`🐄`,
          collarId = arguments.collarId
        )
      )

      val device = deviceService.getDeviceByCollarId(arguments.collarId)
      return Result.success(
        cow.copy(
          collarStatus = device.status,
          lastLocation = device.lastLocation
        )
      )
    } catch (e: Exception) {
      // Future stuff: convert the exception to a domain exception
      return Result.failure(e)
    }
  }
}
