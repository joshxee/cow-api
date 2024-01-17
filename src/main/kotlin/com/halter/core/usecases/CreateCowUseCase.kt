package com.halter.core.usecases

import com.halter.core.arguments.CreateCowArgument
import com.halter.core.domain.Cow
import com.halter.core.repositories.CowRepository
import com.halter.core.services.DeviceService
import jakarta.inject.Singleton

interface CreateCowUseCase {
  fun execute(createCowRequest: CreateCowArgument): Result<Cow>
}

@Singleton
class CreateCowUseCaseImpl(
  private val cowRepository: CowRepository,
  private val deviceService: DeviceService
) : CreateCowUseCase {
  override fun execute(createCowRequest: CreateCowArgument): Result<Cow> {
    val savedCow = cowRepository.save(
      number = createCowRequest.number,
      `🐄` = createCowRequest.`🐄`,
      collarId = createCowRequest.collarId
    )

    savedCow.onSuccess { cow ->
      try {
        val device = deviceService.getDeviceByCollarId(cow.collarId)
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
    return savedCow
  }
}