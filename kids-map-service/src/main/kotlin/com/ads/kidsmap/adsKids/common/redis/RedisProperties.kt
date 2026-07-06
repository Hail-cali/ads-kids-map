package com.ads.kidsmap.adsKids.common.redis

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "dsp.redis")
data class RedisProperties(
    val mode: RedisMode,
    val host: String,
    val port: Int,
    val password: String,
    val cluster: ClusterSetting = ClusterSetting(),
) {
    data class ClusterSetting(
        val nodes: List<String> = emptyList()
    )
}
