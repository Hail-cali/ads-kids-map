package com.ads.kidsmap.adsKids.common.client

import com.ads.kidsmap.adsKids.common.redis.RedisJsonCodec
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.stereotype.Component

interface RedisClient {

    suspend fun getString(
        key: String,
    ): String?

    suspend fun <T : Any> get(
        key: String,
        type: Class<T>,
    ): T?

    suspend fun <T : Any> getIfAbsent(
        key: String,
        type: Class<T>,
        load: suspend (key: String) -> T?
    ): T?
}

/**
 * common redis client impl
 */
@Component
class DefaultRedisClient(
    private val reactiveRedisOperations: ReactiveRedisOperations<String, String>,
    private val codec: RedisJsonCodec,
) : RedisClient {
    override suspend fun getString(
        key: String,
    ) = reactiveRedisOperations.opsForValue()
        .get(key)
        .awaitSingleOrNull()

    override suspend fun <T : Any> get(key: String, type: Class<T>): T? =
        getString(key)?.let {
            codec.decode(it, type)
        }


    override suspend fun <T : Any> getIfAbsent(
        key: String,
        type: Class<T>,
        load: suspend (key: String) -> T?
    ): T? = getString(key)?.let {
        return codec.decode(it, type)
    } ?: load(key)

}