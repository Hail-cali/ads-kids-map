package com.ads.kidsmap.adsKids.kidsmapapi.domain.entity

import org.bson.types.ObjectId

data class PlaceFacade(
    val id: ObjectId,
) {
    companion object {
        fun from(
            places: Places
        ): PlaceFacade {

            return PlaceFacade(
                id = places.id!!
            )
        }
    }
}
