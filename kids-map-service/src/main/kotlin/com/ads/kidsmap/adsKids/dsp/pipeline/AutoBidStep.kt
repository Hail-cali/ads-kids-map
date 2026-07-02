package com.ads.kidsmap.adsKids.dsp.pipeline

import com.ads.kidsmap.adsKids.dsp.engine.DropReason
import com.ads.kidsmap.adsKids.dsp.engine.PipelineResult
import com.ads.kidsmap.adsKids.dsp.engine.PipelineStep
import com.ads.kidsmap.adsKids.dsp.engine.da.DaBidContextBuilder
import org.springframework.stereotype.Component

@Component
class AutoBidStep : PipelineStep<DaBidContextBuilder, DaBidContextBuilder> {
    override val name: String = "GetAutoBidFunction"
    override val dropReason: DropReason = DropReason.AUTO_BID_FAILED

    override suspend fun process(input: DaBidContextBuilder): PipelineResult<DaBidContextBuilder> {
        // TODO get a bidPrice from redis (campaignId + adId -> bidPrice)
        // 상품 의 경우 여러 켐페인에 adId 로 등록되어 있는 경우가 많음
        return PipelineResult.Alive(
            builder = input,
        )
    }
}
