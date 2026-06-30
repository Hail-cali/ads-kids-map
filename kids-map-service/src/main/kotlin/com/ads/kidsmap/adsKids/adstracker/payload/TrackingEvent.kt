package com.ads.kidsmap.adsKids.adstracker.payload

@JvmInline
value class EventTs(val value: Long)

data class TrackingEvent(
    val eventType: EventType,
    val uuid: String,
    val deviceId: String,
    val adId: String, // objectiveType id
    val campaignId: String,
    val requestId: String,
    val objectiveType: ObjectiveType,
    val requestTs: EventTs, // ads requested time stamp
    val eventTs: EventTs,  // event made ts
): KafkaEvent {
    enum class ObjectiveType {
        PRODUCT,
        PLACE,
        EVENT,
        BRAND,
    }
    enum class EventType {
        CLICK,
        IMPRESSION,
        LONG_VIEW
        ;
    }
}
