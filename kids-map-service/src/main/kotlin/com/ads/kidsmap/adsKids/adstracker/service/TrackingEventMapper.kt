package com.ads.kidsmap.adsKids.adstracker.service

import com.ads.kidsmap.adsKids.adstracker.payload.TrackingEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class TrackingEventMapper(
    private val objectMapper: ObjectMapper,
    private val decryptor: TrackingEventDecryptor,
) {
    fun fromString(json: String): TrackingEvent {
        val decrypted = decryptor.decrypt(json)
        return objectMapper.readValue(decrypted, TrackingEvent::class.java)
    }
}
