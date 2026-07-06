package com.ads.kidsmap.adsKids.common.redis.template

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.ReactiveRedisTemplate

@Configuration
class RedisTemplateConfig {

    @Bean
    fun redisTemplateFactory(
        connectionFactory: ReactiveRedisConnectionFactory,
    ): RedisTemplateFactory {
        return RedisTemplateFactory(
            connectionFactory,
        )
    }

    @Bean
    fun reactiveStringRedisTemplate(
        factory: RedisTemplateFactory,
    ): ReactiveRedisTemplate<String, String> {
        return factory.stringTemplate()
    }

    @Bean
    fun reactiveRedisOperations(
        template: ReactiveRedisTemplate<String, String>
    ): ReactiveRedisOperations<String, String> {
        return template
    }
}
