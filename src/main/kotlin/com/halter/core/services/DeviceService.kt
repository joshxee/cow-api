package com.halter.core.services

import com.halter.core.domain.Device

interface DeviceService {
  fun getDeviceByCollarId(collarId: Int): Result<Device>
}
