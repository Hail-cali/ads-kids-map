package com.ads.kidsmap.adsKids.dsp.engine

/**
 * step
 */
interface PipelineStep<I, O> {
    val name: String
    val dropReason: DropReason
    suspend fun process(input: I): PipelineResult<O>
}
