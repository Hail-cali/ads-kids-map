package com.ads.kidsmap.adsKids.dsp.engine

data class BidRequest(
    val requestId: String,
    val inventoryId: String,
    val deviceId: String,
    val seed: Seed,
) {
    data class Seed(
        val placeId: String? = null,
        val eventId: String? = null,
        val goodsId: String? = null,
        val keyword: String? = null,
    ) {
        companion object {
            val EMPTY = Seed()
        }
    }
}
