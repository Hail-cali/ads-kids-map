package com.ads.kidsmap.adsKids.dsp.engine.da

import com.ads.kidsmap.adsKids.dsp.engine.BidContext
import com.ads.kidsmap.adsKids.dsp.engine.BidContextBuilder

data class DaBidContext(
    override val requestId: String,
    override val inventoryId: String,
    override val deviceId: String,
    val candidates: List<Any>,
) : BidContext

class DaBidContextBuilder(
    override val requestId: String,
    override val inventoryId: String,
    override val deviceId: String
) : BidContextBuilder<DaBidContext> {

    val alivedCandidates: MutableList<Any> = mutableListOf()
    val dropedCandidates: MutableList<Any> = mutableListOf()

    override fun build(): DaBidContext {
        return DaBidContext(
            requestId = requestId,
            inventoryId = inventoryId,
            deviceId = deviceId,
            candidates = alivedCandidates.toList(),
        )
    }
}
