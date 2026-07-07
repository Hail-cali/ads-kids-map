package com.ads.kidsmap.adsKids.kidsmapapi.domain.repository

import com.ads.kidsmap.adsKids.kidsmapapi.domain.entity.PlaceMetadata
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

interface PlaceMetadataQueryRepository {
    suspend fun findByPlaceId(
        placeId: ObjectId,
    ): PlaceMetadata?
    suspend fun batchGetPlaceIds(
        placeIds: List<ObjectId>,
    ): Map<ObjectId, PlaceMetadata>
}

@Repository
class PlaceMetadataQueryRepositoryImpl(
    private val reactiveMongoTemplate: ReactiveMongoTemplate,
) : PlaceMetadataQueryRepository {
    override suspend fun findByPlaceId(placeId: ObjectId): PlaceMetadata? {
        val query = Query()

        query.addCriteria(
            Criteria.where(PlaceMetadata::placeId.name).`is`(placeId)
        )

        return reactiveMongoTemplate.find(query, PlaceMetadata::class.java).awaitFirstOrNull()
    }

    override suspend fun batchGetPlaceIds(placeIds: List<ObjectId>): Map<ObjectId, PlaceMetadata> {
        val query = Query()

        query.addCriteria(
            Criteria.where(PlaceMetadata::placeId.name).`in`(placeIds)
        )

        val result = reactiveMongoTemplate.find(query, PlaceMetadata::class.java)

        return result.collectList().awaitSingle().associateBy { it.placeId }
    }
}
