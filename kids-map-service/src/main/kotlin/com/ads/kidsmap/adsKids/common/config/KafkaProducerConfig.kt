package com.ads.kidsmap.adsKids.common.config

import com.ads.kidsmap.adsKids.adstracker.payload.TrackingEvent
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig {

    @Bean
    fun kafkaTemplate(
        @Value("\${spring.kafka.bootstrap-servers}") bootstrapServers: String,
    ): KafkaTemplate<String, TrackingEvent> {
        val props = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,
            ProducerConfig.ACKS_CONFIG to "all",
            ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG to "true",
        )

        val factory = DefaultKafkaProducerFactory<String, TrackingEvent>(props)

        return KafkaTemplate(
            factory,
        )
    }
}
