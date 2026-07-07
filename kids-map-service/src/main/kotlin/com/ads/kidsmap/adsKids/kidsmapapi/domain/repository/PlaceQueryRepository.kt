package com.ads.kidsmap.adsKids.kidsmapapi.domain.repository

import com.ads.kidsmap.adsKids.kidsmapapi.domain.entity.PlaceFacade
import com.ads.kidsmap.adsKids.kidsmapapi.domain.entity.Places
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

interface PlaceQueryRepository {
    suspend fun batchGetPlaces(
        ids: Set<ObjectId>
    ): List<Places>

    suspend fun findById(
        id: ObjectId,
    ): Places?
}

@Repository
class PlaceQueryRepositoryImpl(
    private val reactiveMongoTemplate: ReactiveMongoTemplate,
) : PlaceQueryRepository {
    override suspend fun batchGetPlaces(
        ids: Set<ObjectId>
    ): List<Places> {
        val query = Query()

        query.addCriteria(
            Criteria.where(Places::active.name).`is`(true)
        )

        query.addCriteria(
            Criteria.where(Places::id.name).`in`(*ids.toTypedArray())
        )

        val results = reactiveMongoTemplate.find(query, Places::class.java)

        return results.collectList().awaitSingle()
    }

    override suspend fun findById(
        id: ObjectId,
    ): Places? {
        val query = Query()
        query.addCriteria(
            Criteria.where(Places::id.name).`is`(id)
        )

        val result = reactiveMongoTemplate.findOne(query, Places::class.java)

        return result.awaitFirstOrNull()
    }
}
