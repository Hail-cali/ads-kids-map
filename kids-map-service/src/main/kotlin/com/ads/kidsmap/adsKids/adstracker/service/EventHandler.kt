package com.ads.kidsmap.adsKids.adstracker.service

import com.ads.kidsmap.adsKids.adstracker.payload.KafkaEvent

interface EventHandler<T: KafkaEvent> {
    fun injest(data: String): T
    suspend fun handle(data: String)
    suspend fun publish(event: T)

    suspend fun validate(event: T) {
    }

    suspend fun filter(event: T): Boolean {
        return true
    }

}
