package com.github.arlekinside.slg.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication(
    exclude = [KafkaAutoConfiguration::class]
)
@EnableAspectJAutoProxy
class CoreApplication

fun main(args: Array<String>) {
    runApplication<CoreApplication>(*args)
}
