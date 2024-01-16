package com.halter.adaptor.clients

import com.halter.adaptor.dtos.DeviceStatusResponse
import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.HttpHeaders.ACCEPT
import io.micronaut.http.HttpHeaders.USER_AGENT
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Headers
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("https://5d96585ca824b400141d26b2.mockapi.io")
@Headers(
  Header(name = USER_AGENT, value = "Micronaut HTTP Client"),
  Header(name = ACCEPT, value = "application/json, application/json")
)
interface DeviceClient {
  @Get("/halter/device/{deviceId}/status")
  @SingleResult
  fun fetchStatus(@PathVariable deviceId: Int): List<DeviceStatusResponse>
}
