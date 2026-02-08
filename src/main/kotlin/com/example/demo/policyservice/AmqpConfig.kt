package com.example.demo.policyservice

import org.springframework.amqp.support.converter.JacksonJsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmqpConfig {
    @Bean
    fun messageConverter() = JacksonJsonMessageConverter()
}
