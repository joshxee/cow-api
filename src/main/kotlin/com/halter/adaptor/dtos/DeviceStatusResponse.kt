package com.halter.adaptor.dtos

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

// [{"id":"1","serialNumber":"1","lat":"14.1215","healthy":true,"lng":"76.5347","timestamp":"2020-03-24T20:48:18.838Z"},{"id":"24","serialNumber":"1","lat":"0.1804","healthy":false,"lng":"77.2059","timestamp":"2020-10-19T09:04:20.842Z"},{"id":"94","serialNumber":"1","lat":"-73.2983","healthy":true,"lng":"114.8794","timestamp":"2020-11-24T00:27:39.853Z"},{"id":"98","serialNumber":"1","lat":"63.0169","healthy":false,"lng":"-20.7051","timestamp":"2020-02-11T09:25:54.560Z"},{"id":"99","serialNumber":"1","lat":"43.9917","healthy":true,"lng":"-54.5475","timestamp":"2019-12-13T15:54:25.658Z"}]
@JsonIgnoreProperties(ignoreUnknown = true)
data class DeviceStatusResponse(
  val id: String,
  val serialNumber: String,
  val lat: String,
  val healthy: Boolean,
  val lng: String,
  val timestamp: Date
)
