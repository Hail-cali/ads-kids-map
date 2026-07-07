package com.ads.kidsmap.adsKids.kidsmapapi.service

import com.ads.kidsmap.adsKids.kidsmapapi.domain.entity.PlaceFacade
import com.ads.kidsmap.adsKids.kidsmapapi.domain.repository.PlaceMetadataQueryRepository
import com.ads.kidsmap.adsKids.kidsmapapi.domain.repository.PlaceQueryRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class PlaceQueryService(
    private val placeQueryRepository: PlaceQueryRepository,
    private val placeMetadataQueryRepository: PlaceMetadataQueryRepository,
) {
    suspend fun batchGetPlaces(
        ids: List<String>
    ): List<PlaceFacade> {
        val response = placeQueryRepository.batchGetPlaces(
            ids = ids.map { ObjectId(it) }.toSet()
        )
        val metadataMap = placeMetadataQueryRepository.batchGetPlaceIds(
            placeIds = response.map { it.id!! }
        )

        return response.map {
            PlaceFacade.from(
                places = it,
                placeMetadata = metadataMap[it.id]
            )
        }
    }

    suspend fun findById(
        id: String,
    ): PlaceFacade? {

        val response = placeQueryRepository.findById(
            ObjectId(id)
        ) ?: return null

        return PlaceFacade.from(
            places = response
        )
    }
}
