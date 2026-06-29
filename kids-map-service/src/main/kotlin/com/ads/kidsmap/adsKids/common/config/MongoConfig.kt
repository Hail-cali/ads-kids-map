package com.ads.kidsmap.adsKids.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.convert.NoOpDbRefResolver
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableMongoAuditing
@EnableReactiveMongoRepositories(basePackages = ["com.ads.kidsmap"])
class MongoConfig {

    @Bean
    fun reactiveMongoTemplate(
       mongoDatabaseFactory: ReactiveMongoDatabaseFactory,
       mappingContext: MongoMappingContext,
       customConversions: MongoCustomConversions,
    ): ReactiveMongoTemplate {
        val converter = MappingMongoConverter(
            NoOpDbRefResolver.INSTANCE,
            mappingContext,
        ).apply {
            setCustomConversions(customConversions)
            afterPropertiesSet()
        }

        return ReactiveMongoTemplate(
            mongoDatabaseFactory,
            converter,
        )
    }
}
