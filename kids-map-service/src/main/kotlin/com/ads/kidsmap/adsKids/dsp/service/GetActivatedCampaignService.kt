package com.ads.kidsmap.adsKids.dsp.service

import com.ads.kidsmap.adsKids.dsp.redis.CampaignCacheClient
import org.springframework.stereotype.Service

@Service
class GetActivatedCampaignService(
    private val campaignCacheClient: CampaignCacheClient,
) {
    suspend fun batchGetActivatedCampaigns(
        adIds: List<String>,
    ) {
        adIds.map {
            campaignCacheClient.get(
                it
            )
        }
    }
}