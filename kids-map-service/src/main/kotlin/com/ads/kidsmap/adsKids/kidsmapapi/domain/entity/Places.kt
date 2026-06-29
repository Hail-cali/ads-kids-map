package com.ads.kidsmap.adsKids.kidsmapapi.domain.entity

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.DayOfWeek
import java.time.ZonedDateTime

/**
 * Places
 * index info
 * db.Places.createIndex({})
 *
 */
@TypeAlias("Places")
@Document(collection = "Places")
data class Places(
    @Id
    val id : ObjectId? = null,
    val name: String,
    val description: String,
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    val position: GeoJsonPoint,
    val address: String,
    val phone: String? = null,
    val website: String? = null,
    val businessHours: List<BusinessHour> = emptyList(),
    val active: Boolean,
    val components: List<Component> = emptyList(), // ui component
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime = ZonedDateTime.now(),
) {
    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type"
    )
    @JsonSubTypes(
        JsonSubTypes.Type(Component.Image::class, name = "IMAGE")
    )
    sealed interface Component {
        data class Image(
            val thumbnail: String, // url
        )
    }

    data class BusinessHour(
        val dayOfWeek: DayOfWeek,
        val openTime: String,
        val closeTime: String,
    )
}
