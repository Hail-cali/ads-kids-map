package com.ads.kidsmap.adsKids.kidsmapapi.controller

import com.ads.kidsmap.adsKids.kidsmapapi.domain.entity.PlaceFacade
import com.ads.kidsmap.adsKids.kidsmapapi.service.PlaceQueryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/internal/places")
class AdsKidMapApi(
    private val placeQueryService: PlaceQueryService,
) {
    @GetMapping("/batch")
    suspend fun batchGetPlaces(
        @RequestParam(value = "placeIds", required = false) placeIds: List<String>,
    ) : List<PlaceFacade> {
        val response = placeQueryService.batchGetPlaces(
            placeIds
        )
        return response
    }

    @GetMapping
    suspend fun findById(
        @RequestParam(required = true) id: String,
    ): PlaceFacade {
        val response = placeQueryService.findById(id) ?: throw NoSuchElementException("No place found with id $id")
        return response
    }
}
