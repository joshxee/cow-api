package com.halter.adaptor.services

import com.halter.adaptor.clients.DeviceClient
import com.halter.core.domain.Device
import com.halter.core.domain.LastLocation
import com.halter.core.services.DeviceService
import jakarta.inject.Singleton

@Singleton
class DeviceServiceImpl(
  private val deviceClient: DeviceClient
) : DeviceService {
  override fun getDeviceByCollarId(collarId: Int): Result<Device> {
    try {
      val statuses = deviceClient.fetchStatus(collarId)
      val latestStatus = statuses.maxBy { it.timestamp }
      return Result.success(
        Device(
          status = latestStatus.healthy.let { if (it) "Healthy" else "Broken" }, // Future: Use enum
          lastLocation = LastLocation(
            latitude = latestStatus.lat.toDouble(),
            longitude = latestStatus.lng.toDouble()
          )
        )
      )
    } catch (e: Exception) {
      return Result.failure(e)
    }
  }
}