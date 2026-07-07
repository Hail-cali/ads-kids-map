package com.ads.kidsmap.adsKids.dsp.redis

import com.ads.kidsmap.adsKids.common.client.RedisClient
import org.springframework.stereotype.Component

@Component
class CampaignCacheClient(
    private val redisClient: RedisClient,
) {
    suspend fun get(
        adId: String,
    ): CampaignCacheDto? = redisClient.get(
        keys(adId),
        CampaignCacheDto::class.java
    )

    companion object {
        fun keys(adId: String) = "adid:cam:$adId"
        data class CampaignCacheDto(
            val id: String,
        )
    }
}
