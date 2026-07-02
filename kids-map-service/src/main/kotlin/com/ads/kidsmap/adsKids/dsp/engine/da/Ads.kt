package com.ads.kidsmap.adsKids.dsp.engine.da

data class Ad(
    val itemId: String,
    var adId: String? = null,
    var campaignId: String? = null,
    val qualityScore: Double,
    var auctionResult: AuctionResult? = null,
) {
    data class AuctionResult(
        val bidPrice: Long,
        var winPrice: Long? = null,
    )
}
