package com.halter.adaptor.controllers

import com.halter.adaptor.dtos.CowResponse
import com.halter.adaptor.dtos.CreateCowRequest
import com.halter.adaptor.dtos.LastLocationResponse
import com.halter.core.arguments.CreateCowArgument
import com.halter.core.domain.Cow
import com.halter.core.usecases.CreateCowUseCase
import com.halter.core.usecases.GetAllCowsUseCase
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation

@Controller("/cows")
class CowsController(
  private val createCowUseCase: CreateCowUseCase,
  private val getAllCowsUseCase: GetAllCowsUseCase
) {

  @Operation(summary = "Create a new cow")
  @Post(uri = "", produces = [MediaType.TEXT_JSON], consumes = [MediaType.TEXT_JSON])
  fun create(@Body createCowRequest: CreateCowRequest): CowResponse {
    val arguments = CreateCowArgument(
      number = createCowRequest.number,
      collarId = createCowRequest.collarId,
      `🐄` = createCowRequest.`🐄`
    )
    val result = createCowUseCase.execute(arguments)
      .getOrElse { exception ->
        // Future Stuff: Handle errors
        throw Exception("Unexpected result", exception)
      }
    return result.toResponse()
  }

  @Operation(summary = "Get all cows")
  @Post(uri = "", produces = [MediaType.TEXT_JSON], consumes = [MediaType.TEXT_JSON])
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
}