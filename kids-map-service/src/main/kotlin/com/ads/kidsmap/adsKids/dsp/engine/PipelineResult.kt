package com.ads.kidsmap.adsKids.dsp.engine

sealed interface PipelineResult<out T> {

    data class Alive<T>(
        val builder: T,
    ) : PipelineResult<T>

    data class Dropped(
        val reason: DropReason,
    ): PipelineResult<Nothing>
}

suspend inline fun <T, R> PipelineResult<T>.then(
    crossinline next: suspend (T) -> PipelineResult<R>,
): PipelineResult<R> {
    return when (this) {
        is PipelineResult.Alive -> next(builder)
        is PipelineResult.Dropped -> this
    }
}
