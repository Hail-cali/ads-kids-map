package com.ads.kidsmap.adsKids.kidsmapapi.service

import com.ads.kidsmap.adsKids.kidsmapapi.domain.entity.PlaceFacade
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class PlaceServingService(
    private val queryService: PlaceQueryService,
) {
    suspend fun batchGetPlaces(
        ids: List<String>
    ): List<PlaceFacade> {
        return queryService.batchGetPlaces(ids)
    }

    suspend fun findById(
        id: String,
    ): PlaceFacade? {
        return queryService.findById(id)
    }

    companion object {
        val logger: KLogger = KotlinLogging.logger {}
    }
}
