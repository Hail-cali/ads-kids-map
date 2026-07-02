package com.ads.kidsmap.adsKids.dsp.pipeline

import com.ads.kidsmap.adsKids.common.client.RankerClient
import com.ads.kidsmap.adsKids.common.client.RankerDto
import com.ads.kidsmap.adsKids.dsp.engine.DropReason
import com.ads.kidsmap.adsKids.dsp.engine.PipelineResult
import com.ads.kidsmap.adsKids.dsp.engine.PipelineStep
import com.ads.kidsmap.adsKids.dsp.engine.da.Ad
import com.ads.kidsmap.adsKids.dsp.engine.da.DaBidContextBuilder
import org.springframework.stereotype.Component

@Component
class LoadCandidateStep(
    private val rankerClient: RankerClient,
) : PipelineStep<DaBidContextBuilder, DaBidContextBuilder> {
    override val name = "MlRanker"
    override val dropReason = DropReason.NO_CANDIDATE

    override suspend fun process(builder: DaBidContextBuilder): PipelineResult<DaBidContextBuilder> {
        val rankerResponse = rankerClient.call(
            request = RankerDto.Request(
                requestId = builder.requestId,
                inventoryId = builder.inventoryId,
                seed = RankerDto.Seed(
                    placeId = builder.seed.placeId,
                    eventId = builder.seed.eventId,
                    keyword = builder.seed.keyword,
                )
            )
        )

        rankerResponse.adCandidates.map { candidate ->
            Ad(
                itemId = candidate.adId,
                qualityScore = candidate.rawQualityScore
            )
        }.takeIf { it.isNotEmpty() }?.also { candidates ->
            builder.alivedCandidates.addAll(candidates)
        } ?: return PipelineResult.Dropped(
            reason = dropReason
        )

        return PipelineResult.Alive(
            builder = builder
        )
    }
}
