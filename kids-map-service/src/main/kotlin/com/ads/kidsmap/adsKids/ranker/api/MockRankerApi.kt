package com.ads.kidsmap.adsKids.ranker.api

import com.ads.kidsmap.adsKids.ranker.api.MockRanker.Request
import org.springframework.stereotype.Component

@Component
class MockRankerApi {
    suspend fun getPlaceCandidate(
        request: Request
    ): MockRanker.Response = MOCK_RESPONSE

    companion object {
        val MOCK_RESPONSE = MockRanker.Response(
            adCandidates = listOf(
                MockRanker.AdCandidate("12", 0.1),
                MockRanker.AdCandidate("101", 0.5),
                MockRanker.AdCandidate("102", 0.7),
            ),
            producerId = "RAW_MOCK_LR"
        )
    }
}

object MockRanker {
    data class Request(
        val requestId: String,
        val inventoryId: String, // 추천 랭커에서 사용 안했으면 좋겠는데 현실적으로 인벤토리마다 ranker 로직이 필요함
        val seed: Seed,
    )

    data class Response(
        val adCandidates: List<AdCandidate>,
        val producerId: String,
    )

    data class AdCandidate(
        val adId: String,
        val rawQualityScore: Double,
    )

    data class Seed(
        val placeId: String? = null,
        val eventId: String? = null,
        val keyword: String? = null, // for sa
    )
}
