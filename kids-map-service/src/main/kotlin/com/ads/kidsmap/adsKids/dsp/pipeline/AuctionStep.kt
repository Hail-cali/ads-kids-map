package com.ads.kidsmap.adsKids.dsp.pipeline

import com.ads.kidsmap.adsKids.dsp.engine.DropReason
import com.ads.kidsmap.adsKids.dsp.engine.PipelineResult
import com.ads.kidsmap.adsKids.dsp.engine.PipelineStep
import com.ads.kidsmap.adsKids.dsp.engine.da.DaBidContextBuilder
import org.springframework.stereotype.Component

@Component
class AuctionStep : PipelineStep<DaBidContextBuilder, DaBidContextBuilder> {
    override val name = "auction"
    override val dropReason: DropReason = DropReason.AUCTION_DROPPED

    override suspend fun process(input: DaBidContextBuilder): PipelineResult<DaBidContextBuilder> {

        return PipelineResult.Alive(
            builder = input,
        )
    }
}
