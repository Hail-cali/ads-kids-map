package com.ads.kidsmap.adsKids.adstracker.api

import com.ads.kidsmap.adsKids.adstracker.service.TrackerEventHandler
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * ingest trackers into event stream
 */
@RestController
@RequestMapping("/ads/trackers")
class AdsTracker(
    private val trackingEventProducer: TrackerEventHandler,
) {

    @PostMapping("/v1")
    suspend fun trackers(
        @RequestBody data: String,
    ): ResponseEntity<String> {
        runCatching {
            trackingEventProducer.handle(
                data
            )
        }.onFailure { exception ->
            logger.warn(exception) {
                "failed to handle tracker event"
            }
        }
        // do not allow to guess tracker's status
        return ResponseEntity.ok().build()
    }

    @GetMapping("/v1")
    suspend fun debug(
        @RequestParam("data") data: String? = null,
    ): ResponseEntity<String> {

        logger.info {
            "abuse tracker $data"
        }

        return ResponseEntity.ok().build()
    }

    companion object {
        val logger: KLogger = KotlinLogging.logger {}
    }
}
