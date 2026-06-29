package com.ads.kidsmap.adsKids.kidsmapapi.domain.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@TypeAlias("PlaceMetadata")
@Document(collection = "PlaceMetadata")
data class PlaceMetadata(
    @Id
    val id: ObjectId? = null,
    val placeId: ObjectId, // Places objectId, FK OneToOne
    val viewCounter : Counter = Counter(),
    val scrapCounter: Counter = Counter(),
    val praiseCount: Counter = Counter(),
    val reviewCounter: Counter = Counter(),
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime = ZonedDateTime.now(),
) {
    data class Counter(
        val count: Int = 0,
    )
}
