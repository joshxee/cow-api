package com.halter.core.usecases

import com.halter.core.arguments.CreateCowArgument
import com.halter.core.domain.Cow
import com.halter.core.repositories.CowRepository
import jakarta.inject.Singleton

interface CreateCowUseCase {
  fun execute(createCowRequest: CreateCowArgument): Result<Cow>
}

@Singleton
class CreateCowUseCaseImpl(
  private val cowRepository: CowRepository
) : CreateCowUseCase {
  override fun execute(createCowRequest: CreateCowArgument): Result<Cow> {
    val savedCow = cowRepository.save(
      number = createCowRequest.number,
      `🐄` = createCowRequest.`🐄`,
      collarId = createCowRequest.collarId
    )
    return savedCow
  }
}