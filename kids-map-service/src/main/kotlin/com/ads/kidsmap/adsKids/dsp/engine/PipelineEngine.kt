package com.ads.kidsmap.adsKids.dsp.engine

import org.springframework.stereotype.Component

@Component
class PipelineEngine {

    suspend fun <REQ, CTX> execute(
        request: REQ,
        spec: PipelineSpec<REQ, CTX>,
    ): PipelineResult<CTX> = spec.process(request)
}

fun interface PipelineSpec<REQ, CTX> {
    suspend fun process(request: REQ): PipelineResult<CTX>
}
