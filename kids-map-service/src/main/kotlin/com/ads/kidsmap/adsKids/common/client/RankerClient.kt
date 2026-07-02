package com.ads.kidsmap.adsKids.common.client

import com.ads.kidsmap.adsKids.common.client.RankerDto.AdCandidate
import com.ads.kidsmap.adsKids.ranker.api.MockRanker
import com.ads.kidsmap.adsKids.ranker.api.MockRankerApi
import org.springframework.stereotype.Component


@Component
class RankerClient(
    private val rankerApi: MockRankerApi
) : GrpcClient<RankerDto.Request, RankerDto.Response> {
    override suspend fun call(
        request: RankerDto.Request,
    ): RankerDto.Response {
        val response = rankerApi.getPlaceCandidate(
            request = MockRanker.Request(
                requestId = request.requestId,
                inventoryId = request.inventoryId,
                seed = MockRanker.Seed(
                    placeId = request.seed.placeId,
                    eventId = request.seed.eventId,
                    keyword = request.seed.keyword,
                )
            ),
        )

        return RankerDto.Response(
            producerId = response.producerId,
            adCandidates = response.adCandidates.map {
                AdCandidate.from(it)
            }
        )
    }
}

object RankerDto {
    data class Request(
        val requestId: String,
        val inventoryId: String,
        val seed: Seed,
    )

    data class Response(
        val adCandidates: List<AdCandidate>,
        val producerId: String,
    )

    data class AdCandidate(
        val adId: String,
        val rawQualityScore: Double,
    ) {
        companion object {
            fun from(
                rankerCandidate: MockRanker.AdCandidate,
            ): AdCandidate {
                return AdCandidate(
                    adId = rankerCandidate.adId,
                    rawQualityScore = rankerCandidate.rawQualityScore,
                )
            }
        }
    }

    data class Seed(
        val placeId: String? = null,
        val eventId: String? = null,
        val keyword: String? = null, // for sa
    )
}
