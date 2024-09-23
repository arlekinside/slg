package com.github.arlekinside.slg.core.config

import com.github.arlekinside.slg.core.repos.UserRepo
import com.github.arlekinside.slg.core.services.balance.BalanceProcessor
import com.github.arlekinside.slg.core.services.balance.DefaultBalanceProcessor
import com.github.arlekinside.slg.core.services.balance.KafkaBalanceProcessor
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.kafka.core.KafkaTemplate

@Configuration
@EnableR2dbcRepositories
class BalanceProcessorAutoConfiguration {

    @ConditionalOnBean(KafkaTemplate::class)
    @Bean
    fun kafkaBalanceProcessor(): BalanceProcessor {
        return KafkaBalanceProcessor()
    }

    @ConditionalOnMissingBean
    @Bean
    fun defaultBalanceProcessor(userRepo: UserRepo): BalanceProcessor {
        return DefaultBalanceProcessor(userRepo)
    }

}