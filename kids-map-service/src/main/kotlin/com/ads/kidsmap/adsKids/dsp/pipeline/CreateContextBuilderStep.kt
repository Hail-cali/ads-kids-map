package com.ads.kidsmap.adsKids.dsp.pipeline

import com.ads.kidsmap.adsKids.dsp.engine.BidRequest
import com.ads.kidsmap.adsKids.dsp.engine.DropReason
import com.ads.kidsmap.adsKids.dsp.engine.PipelineResult
import com.ads.kidsmap.adsKids.dsp.engine.PipelineStep
import com.ads.kidsmap.adsKids.dsp.engine.da.DaBidContextBuilder
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

@Component
class CreateContextBuilderStep : PipelineStep<BidRequest, DaBidContextBuilder> {
    override val name = "Builder"
    override val dropReason: DropReason = DropReason.NON_VALID_REQUEST

    override suspend fun process(
        input: BidRequest
    ): PipelineResult<DaBidContextBuilder> {
        if (input.requestId.isBlank()) {
            return PipelineResult.Dropped(dropReason)
        }
        return PipelineResult.Alive(
            DaBidContextBuilder(
                requestId = input.requestId,
                inventoryId = input.inventoryId,
                deviceId = input.deviceId,
            )
        )
    }

    companion object {
        val logger: KLogger = KotlinLogging.logger {}
    }
}
