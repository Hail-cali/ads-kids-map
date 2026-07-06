package com.ads.kidsmap.adsKids.common.config

import com.ads.kidsmap.adsKids.common.redis.RedisProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(RedisProperties::class)
class RedisConfig {

}
