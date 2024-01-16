package com.halter.adaptor.controllers

import com.halter.adaptor.dtos.CowRequest
import com.halter.adaptor.dtos.CowResponse
import com.halter.adaptor.dtos.LastLocationResponse
import com.halter.adaptor.persistence.UpdateCowUseCase
import com.halter.core.arguments.CreateCowArgument
import com.halter.core.arguments.UpdateCowArgument
import com.halter.core.domain.Cow
import com.halter.core.usecases.CreateCowUseCase
import com.halter.core.usecases.GetAllCowsUseCase
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.openapi.annotation.OpenAPIGroup
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.swagger.v3.oas.annotations.Operation

@Controller("/cows")
@OpenAPIGroup("Cows")
@ExecuteOn(TaskExecutors.BLOCKING)
class CowsController(
  private val createCowUseCase: CreateCowUseCase,
  private val getAllCowsUseCase: GetAllCowsUseCase,
  private val updateCowUseCase: UpdateCowUseCase
) {

  @Operation(summary = "Create a new cow")
  @Post(uri = "/", produces = [MediaType.TEXT_JSON], consumes = [MediaType.TEXT_JSON])
  fun create(@Body cowRequest: CowRequest): CowResponse {
    val arguments = CreateCowArgument(
      number = cowRequest.number,
      collarId = cowRequest.collarId,
      `🐄` = cowRequest.`🐄`
    )
    val result = createCowUseCase.execute(arguments)
      .getOrElse { exception ->
        // Future Stuff: Handle errors
        throw Exception("Unexpected result", exception)
      }
    return result.toResponse()
  }

  @Operation(summary = "Get all cows")
  @Get(uri = "/", produces = [MediaType.TEXT_JSON], consumes = [MediaType.TEXT_JSON])
  fun getAll(): List<CowResponse> {
    val cows = getAllCowsUseCase.execute()
      .getOrElse { exception ->
        // Future Stuff: Handle errors
        throw Exception("Unexpected result", exception)
      }

    // Future Stuff: Handle this in a tested converter
    return cows.map { cow -> cow.toResponse() }
  }

  private fun Cow.toResponse() = CowResponse(
    id = this.id,
    cowNumber = this.number.toString(),
    collarId = this.collarId.toString(),
    `🐄` = this.`🐄`,
    collarStatus = this.collarStatus,
    lastLocation = this.lastLocation.let { lastLocation ->
      lastLocation?.let {
        LastLocationResponse(
          latitude = it.latitude,
          longitude = it.longitude
        )
      }
    }
  )

  @Operation(summary = "Update a cow by id")
  @Post(uri = "/{id}", produces = [MediaType.TEXT_JSON], consumes = [MediaType.TEXT_JSON])
  fun update(cowRequest: CowRequest, @PathVariable id: String): CowResponse {
    val arguments = UpdateCowArgument(
      id = id,
      number = cowRequest.number,
      collarId = cowRequest.collarId,
      `🐄` = cowRequest.`🐄`
    )
    val result = updateCowUseCase.execute(arguments)
      .getOrElse { exception ->
        // Future Stuff: Handle errors
        throw Exception("Unexpected result", exception)
      }
    return result.toResponse()
  }
}