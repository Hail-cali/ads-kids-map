package com.ads.kidsmap.adsKids.common.redis.template

import com.ads.kidsmap.adsKids.common.redis.RedisTemplatePresets
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext

class RedisTemplateFactory(
    private val connectionFactory: ReactiveRedisConnectionFactory,
) {
    fun stringTemplate(): ReactiveRedisTemplate<String, String> {
        return create(
            spec = RedisTemplatePresets.build()
        )
    }

    fun <K: Any, V: Any> create(
        spec: RedisTemplatePresets.RedisTemplateSpec<K, V>,
    ): ReactiveRedisTemplate<K, V> {
        val ctx = RedisSerializationContext
            .newSerializationContext<K, V>(spec.keySerializer)
            .key(spec.keySerializer)
            .value(spec.valueSerializer)
            .build()

        return ReactiveRedisTemplate(
            connectionFactory,
            ctx
        )
    }
}
