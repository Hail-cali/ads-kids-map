package com.ads.kidsmap.adsKids.common.redis

import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


object RedisTemplatePresets {

    fun build(): RedisTemplateSpec<String, String> {
        val serializer = StringRedisSerializer()

        return RedisTemplateSpec(
            keySerializer = serializer,
            valueSerializer = serializer
        )
    }

    data class RedisTemplateSpec<K: Any, V: Any>(
        val keySerializer: RedisSerializer<K>,
        val valueSerializer: RedisSerializer<V>,
    )
}
