package com.halter.adaptor.services

import com.halter.core.domain.Device
import com.halter.core.services.DeviceService
import jakarta.inject.Singleton

@Singleton
class DeviceServiceImpl : DeviceService {
  override fun getDeviceByCollarId(collarId: Int): Result<Device> {
    TODO("Not yet implemented")
  }
}