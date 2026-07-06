package com.ads.kidsmap.adsKids.dsp.service

import com.ads.kidsmap.adsKids.dsp.redis.CampaignCacheClient
import org.springframework.stereotype.Service


@Service
class GetActivatedCampaignService(
    private val campaignCacheClient: CampaignCacheClient,
) {
    suspend fun batchGetActivatedCampaigns(
        campaignIds: List<String>,
    ) {
        campaignIds.map {
            campaignCacheClient.get(
                it
            )
        }
    }
}