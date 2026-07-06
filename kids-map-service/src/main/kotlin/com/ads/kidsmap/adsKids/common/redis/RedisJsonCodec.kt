package com.ads.kidsmap.adsKids.common.redis

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class RedisJsonCodec(
    private val objectMapper: ObjectMapper,
) {
    fun <T: Any> encode(
        value: T,
    ): String = objectMapper.writeValueAsString(value)

    fun <T: Any> decode(
        rawString: String,
        type: Class<T>,
    ) = objectMapper.readValue(rawString, type)
}
