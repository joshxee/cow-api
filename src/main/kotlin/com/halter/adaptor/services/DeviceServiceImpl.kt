package com.halter.adaptor.services

import com.halter.adaptor.clients.DeviceClient
import com.halter.core.domain.Device
import com.halter.core.domain.LastLocation
import com.halter.core.services.DeviceService
import io.micronaut.cache.annotation.CacheConfig
import io.micronaut.cache.annotation.Cacheable
import jakarta.inject.Singleton

@Singleton
@CacheConfig("devices")
open class DeviceServiceImpl(
  private val deviceClient: DeviceClient
) : DeviceService {
  @Cacheable
  override fun getDeviceByCollarId(collarId: Int): Device {
    val statuses = deviceClient.fetchStatus(collarId)
    val latestStatus = statuses.maxBy { it.timestamp }
    return Device(
      status = latestStatus.healthy.let { if (it) "Healthy" else "Broken" }, // Future: Use enum
      lastLocation = LastLocation(
        latitude = latestStatus.lat.toDouble(),
        longitude = latestStatus.lng.toDouble()
      )
    )
  }
}