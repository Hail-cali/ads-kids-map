package com.ads.kidsmap.adsKids.dsp.pipeline

import com.ads.kidsmap.adsKids.dsp.engine.DropReason
import com.ads.kidsmap.adsKids.dsp.engine.PipelineResult
import com.ads.kidsmap.adsKids.dsp.engine.PipelineStep
import com.ads.kidsmap.adsKids.dsp.engine.da.DaBidContext
import com.ads.kidsmap.adsKids.dsp.engine.da.DaBidContextBuilder
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

@Component
class BuildFinalContextStep : PipelineStep<DaBidContextBuilder, DaBidContext> {
    override val name = "BuildFinal"
    override val dropReason = DropReason.UNSPECIFIED

    override suspend fun process(
        builder: DaBidContextBuilder,
    ): PipelineResult<DaBidContext> {
        return PipelineResult.Alive(
            builder.build()
        )
    }

    companion object {
        val logger: KLogger = KotlinLogging.logger {}
    }
}
