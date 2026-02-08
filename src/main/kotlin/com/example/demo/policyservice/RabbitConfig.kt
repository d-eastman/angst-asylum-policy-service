package com.example.demo.policyservice

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    companion object {
        const val POLICY_EXCHANGE = "policy.exchange"
        const val POLICY_INSERTED_QUEUE = "policy.inserted.queue"
        const val POLICY_INSERTED_ROUTING_KEY = "policy.inserted"
    }

    @Bean
    fun policyExchange(): DirectExchange =
        DirectExchange(POLICY_EXCHANGE)

    @Bean
    fun policyCreatedQueue(): Queue =
        Queue(POLICY_INSERTED_QUEUE, true)

    @Bean
    fun policyCreatedBinding(): Binding =
        BindingBuilder
            .bind(policyCreatedQueue())
            .to(policyExchange())
            .with(POLICY_INSERTED_ROUTING_KEY)
}
