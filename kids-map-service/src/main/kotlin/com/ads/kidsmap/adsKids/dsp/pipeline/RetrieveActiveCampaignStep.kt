package com.ads.kidsmap.adsKids.dsp.pipeline

import com.ads.kidsmap.adsKids.dsp.engine.DropReason
import com.ads.kidsmap.adsKids.dsp.engine.PipelineResult
import com.ads.kidsmap.adsKids.dsp.engine.PipelineStep
import com.ads.kidsmap.adsKids.dsp.engine.da.DaBidContextBuilder
import org.springframework.stereotype.Component

@Component
class RetrieveActiveCampaignStep : PipelineStep<DaBidContextBuilder, DaBidContextBuilder> {
    override val name: String = "RetrieveActiveCampaignStep"
    override val dropReason: DropReason = DropReason.NO_CANDIDATE

    override suspend fun process(
        input: DaBidContextBuilder
    ): PipelineResult<DaBidContextBuilder> {
        input.alivedCandidates
        return PipelineResult.Alive(
            builder = input
        )
    }
}
