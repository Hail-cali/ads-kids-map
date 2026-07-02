package com.ads.kidsmap.adsKids.dsp.engine

interface BidContext {
    val requestId: String
    val inventoryId: String
    val deviceId: String
}

interface BidContextBuilder<CTX: BidContext> {
    val requestId: String
    val inventoryId: String
    val deviceId: String

    fun build(): CTX
}
