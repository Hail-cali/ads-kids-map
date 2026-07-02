package com.ads.kidsmap.adsKids.dsp.engine.da

import com.ads.kidsmap.adsKids.dsp.engine.BidContext
import com.ads.kidsmap.adsKids.dsp.engine.BidContextBuilder
import com.ads.kidsmap.adsKids.dsp.engine.BidRequest

data class DaBidContext(
    override val requestId: String,
    override val inventoryId: String,
    override val deviceId: String,
    val seed: BidRequest.Seed,
    val alivedAds: List<Ad>,
    val dropedAds: List<Ad>,
) : BidContext

class DaBidContextBuilder(
    override val requestId: String,
    override val inventoryId: String,
    override val deviceId: String,
) : BidContextBuilder<DaBidContext> {
    val alivedCandidates: MutableList<Ad> = mutableListOf()
    val dropedCandidates: MutableList<Ad> = mutableListOf()

    var seed: BidRequest.Seed = BidRequest.Seed.EMPTY


    override fun build(): DaBidContext {
        return DaBidContext(
            requestId = requestId,
            inventoryId = inventoryId,
            deviceId = deviceId,
            seed = seed,
            alivedAds = alivedCandidates.toList(),
            dropedAds = dropedCandidates.toList(),
        )
    }
}
