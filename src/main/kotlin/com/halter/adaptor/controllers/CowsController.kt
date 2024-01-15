package com.halter.adaptor.controllers

import com.halter.adaptor.dtos.CreateCowRequest
import com.halter.adaptor.dtos.CreateCowResponse
import com.halter.core.arguments.CreateCowArgument
import com.halter.core.usecases.CreateCowUseCase
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation

@Controller("/cows")
class CowsController(private val createCowUseCase: CreateCowUseCase) {

  @Operation(summary = "Create a new cow")
  @Post(uri = "/", produces = ["text/json"], consumes = ["text/json"])
  fun create(@Body createCowRequest: CreateCowRequest): CreateCowResponse {
    val arguments = CreateCowArgument(
      number = createCowRequest.number,
      collarId = createCowRequest.collarId,
      `🐄` = createCowRequest.`🐄`
    )
    val result = createCowUseCase.execute(arguments)
    when {
      result.isSuccess -> {
        val cow = result.getOrNull()!!
        return CreateCowResponse(
          id = cow.id,
          number = cow.number,
          collarId = cow.collarId,
          `🐄` = cow.`🐄`
        )
      }
      else -> {
        // Future Stuff: Handle errors
        throw Exception("Unexpected result")
      }
    }
  }
}