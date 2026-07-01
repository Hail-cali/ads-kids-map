package com.ads.kidsmap.adsKids.adstracker.service

import com.ads.kidsmap.adsKids.adstracker.payload.TrackingEvent
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.future.await
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class TrackerEventHandler(
    private val kafkaTemplate: KafkaTemplate<String, TrackingEvent>,
    private val eventMapper: TrackingEventMapper,
) : EventHandler<TrackingEvent> {
    override fun injest(
        data: String
    ): TrackingEvent {
        return eventMapper.fromString(data)
    }

    override suspend fun handle(
        data: String
    ) {
        injest(data).takeIfSuspend { event ->
            filter(event)
        }?.also {
            validate(it)
        }?.also {
            validate(it)
        }
    }

    override suspend fun publish(event: TrackingEvent) {
        kafkaTemplate.send(
            TOPIC, event.requestId, event
        ).await()
    }

    companion object {
        val logger = KotlinLogging.logger {}
        const val TOPIC = "ads-da-tracking-events"
    }
}
