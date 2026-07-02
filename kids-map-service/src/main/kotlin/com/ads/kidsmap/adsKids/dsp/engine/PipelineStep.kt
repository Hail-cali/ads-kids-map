package com.ads.kidsmap.adsKids.dsp.engine

/**
 * step
 */
interface PipelineStep<IN, OUT> {
    val name: String
    val dropReason: DropReason
    suspend fun process(input: IN): PipelineResult<OUT>
}
