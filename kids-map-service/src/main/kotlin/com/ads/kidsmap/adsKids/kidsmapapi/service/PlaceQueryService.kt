package com.ads.kidsmap.adsKids.kidsmapapi.service

import com.ads.kidsmap.adsKids.kidsmapapi.domain.entity.PlaceFacade
import com.ads.kidsmap.adsKids.kidsmapapi.domain.repository.PlaceQueryRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class PlaceQueryService(
    private val placeQueryRepository: PlaceQueryRepository,
) {
    suspend fun batchGetPlaces(
        ids: List<String>
    ): List<PlaceFacade> {
        val response = placeQueryRepository.batchGetPlaces(
            ids = ids.map { ObjectId(it) }.toSet()
        )
        return response
    }

    suspend fun findById(
        id: String,
    ): PlaceFacade? {

        return placeQueryRepository.findById(
            ObjectId(id)
        )
    }
}
