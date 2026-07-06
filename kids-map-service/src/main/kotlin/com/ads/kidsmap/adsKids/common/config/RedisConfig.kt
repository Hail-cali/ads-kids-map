package com.ads.kidsmap.adsKids.common.config

import com.ads.kidsmap.adsKids.common.redis.RedisMode
import com.ads.kidsmap.adsKids.common.redis.RedisProperties
import io.lettuce.core.ClientOptions
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisClusterConfiguration
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import java.time.Duration

@Configuration
@EnableConfigurationProperties(RedisProperties::class)
class RedisConfig {

    @Bean
    fun redisConnectionFactory(
        properties: RedisProperties,
    ): LettuceConnectionFactory = when (properties.mode) {
        RedisMode.STANDALONE -> standaloneConnectionFactory(properties)
        RedisMode.CLUSTER -> clusterConnectionFactory(properties)
    }

    private fun standaloneConnectionFactory(
        properties: RedisProperties,
    ): LettuceConnectionFactory {
        val redisConfig = RedisStandaloneConfiguration().apply {
            hostName = properties.host
            port = properties.port
        }

        val clientConfig = LettuceClientConfiguration.builder()
            .commandTimeout(
                Duration.ofMillis(properties.commandTimeoutMs)
            )
            .clientOptions(
                ClientOptions.builder()
                    .autoReconnect(true)
                    .disconnectedBehavior(
                        ClientOptions.DisconnectedBehavior.REJECT_COMMANDS
                    )
                    .build()
            )
            .build()

        return LettuceConnectionFactory(redisConfig, clientConfig)
    }

    private fun clusterConnectionFactory(
        properties: RedisProperties,
    ): LettuceConnectionFactory {
        val redisConfig = RedisClusterConfiguration(
            properties.cluster.nodes,
        ).apply {
            maxRedirects = MAX_REDIRECT_OPTION
            properties.password?.apply {
                password = RedisPassword.of(
                    properties.password,
                )
            }
        }

        val clientConfiig = LettuceClientConfiguration.builder()
            .commandTimeout(
                Duration.ofMillis(properties.commandTimeoutMs)
            )
            .clientOptions(
                ClientOptions.builder()
                    .autoReconnect(true)
                    .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                    .build()
            )
            .build()

        return LettuceConnectionFactory(redisConfig, clientConfiig)
    }

    companion object {
        const val MAX_REDIRECT_OPTION = 3
    }
}
