package com.ads.kidsmap.adsKids.dsp.redis

import com.ads.kidsmap.adsKids.common.client.RedisClient
import org.springframework.stereotype.Component

@Component
class CampaignCacheClient(
    private val redisClient: RedisClient,
) {
    suspend fun get(
        campaignId: String,
    ): CampaignCacheDto? = redisClient.get(campaignId, CampaignCacheDto::class.java)

    companion object {
        data class CampaignCacheDto(
            val id: String,
        )
    }
}
