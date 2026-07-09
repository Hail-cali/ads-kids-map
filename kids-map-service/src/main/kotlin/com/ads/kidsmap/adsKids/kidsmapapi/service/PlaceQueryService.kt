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
        if (ids.isEmpty()) {
            return emptyList()
        }

        val response = placeQueryRepository.batchGetPlaces(
            ids = ids.map(::toObjectId).toSet()
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

        val placeId = toObjectId(id)
        val response = placeQueryRepository.findById(
            placeId
        ) ?: return null
        val placeMetadata = placeMetadataQueryRepository.findByPlaceId(
            placeId
        )

        return PlaceFacade.from(
            places = response,
            placeMetadata = placeMetadata,
        )
    }

    private fun toObjectId(
        id: String,
    ): ObjectId {
        require(ObjectId.isValid(id)) {
            "Invalid ObjectId: $id"
        }

        return ObjectId(id)
    }
}
