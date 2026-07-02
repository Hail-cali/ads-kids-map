package com.ads.kidsmap.adsKids.dsp.pipeline

import com.ads.kidsmap.adsKids.dsp.engine.BidRequest
import com.ads.kidsmap.adsKids.dsp.engine.PipelineEngine
import com.ads.kidsmap.adsKids.dsp.engine.PipelineResult
import com.ads.kidsmap.adsKids.dsp.engine.PipelineSpec
import com.ads.kidsmap.adsKids.dsp.engine.da.DaBidContext
import com.ads.kidsmap.adsKids.dsp.engine.then
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

@Component
class DaBidPipeline(
    private val engine: PipelineEngine,
    private val createContextBuilderStep: CreateContextBuilderStep,
    private val buildFinalContextStep: BuildFinalContextStep,
) {
    suspend fun execute(
        request: BidRequest
    ): PipelineResult<DaBidContext> = engine.execute(
        request = request,
        spec = PipelineSpec { bidRequest ->
            createContextBuilderStep.process(bidRequest)
                .then {
                    buildFinalContextStep.process(it)
                }
        }
    )

    companion object {
        val logger: KLogger = KotlinLogging.logger {}
    }
}
