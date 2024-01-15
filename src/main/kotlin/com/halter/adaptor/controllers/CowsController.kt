package com.halter.adaptor.controllers

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/cows")
class CowsController {

  @Get(uri = "/", produces = ["text/plain"])
  fun index(): String {
    return "Example Response"
  }
}