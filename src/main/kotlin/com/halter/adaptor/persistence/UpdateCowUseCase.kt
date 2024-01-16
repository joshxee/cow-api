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
    val cowResult = cowRepository.update(
      Cow(
        id = arguments.id,
        number = arguments.number,
        collarId = arguments.collarId
      )
    )

    cowResult.onSuccess { cow ->
      val deviceResult = deviceService.getDeviceByCollarId(arguments.collarId)
      deviceResult.onSuccess { device ->
        return Result.success(
          cow.copy(
            collarStatus = device.status,
            lastLocation = device.lastLocation
          )
        )
      }
      deviceResult.onFailure {
        // Future: Either log or handle the error
      }
    }

    return cowResult
  }
}
