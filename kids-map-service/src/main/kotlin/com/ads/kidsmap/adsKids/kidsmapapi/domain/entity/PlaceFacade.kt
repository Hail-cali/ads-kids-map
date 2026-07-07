package com.ads.kidsmap.adsKids.kidsmapapi.domain.entity

import com.ads.kidsmap.adsKids.kidsmapapi.domain.entity.PlaceMetadata.Counter
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import java.time.ZonedDateTime

data class PlaceFacade(
    val placeId: ObjectId,
    val name: String,
    val description: String,
    val position: GeoJsonPoint,
    val businessHours: List<Places.BusinessHour>,
    val component: List<Places.Component>,
    val viewCounter : Counter = Counter(),
    val scrapCounter: Counter = Counter(),
    val praiseCount: Counter = Counter(),
    val reviewCounter: Counter = Counter(),
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
) {
    companion object {
        fun from(
            places: Places,
            placeMetadata: PlaceMetadata? = null,
        ): PlaceFacade {

            return PlaceFacade(
                placeId = places.id!!,
                name = places.name,
                description = places.description,
                position = places.position,
                businessHours = places.businessHours,
                component = places.components,
                viewCounter = placeMetadata?.viewCounter ?: Counter(),
                scrapCounter = placeMetadata?.scrapCounter ?: Counter(),
                praiseCount = placeMetadata?.praiseCount ?: Counter(),
                reviewCounter = placeMetadata?.reviewCounter ?: Counter(),
                createdAt = places.createdAt,
                updatedAt = places.updatedAt,
            )
        }
    }
}
