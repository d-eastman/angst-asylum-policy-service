package com.example.demo.policyservice

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Component
class OutboxPublisher(
    private val repository: OutboxEventRepository,
    private val rabbitTemplate: RabbitTemplate
) {

    private val logger = LoggerFactory.getLogger(PolicyController::class.java)

    @Scheduled(fixedDelay = 5000)
    @Transactional
    fun publish() {
        val events = repository.findUnpublished()
        println("publishing ${events.size} events")

        events.forEach { event ->
            try {
                println("unpublished event: $event")
                rabbitTemplate.convertAndSend(
                    RabbitConfig.POLICY_EXCHANGE,
                    RabbitConfig.POLICY_INSERTED_ROUTING_KEY,
                    event.payload
                )

                event.publishedAt = Instant.now()
                repository.save(event)
            } catch (ex: Exception) {
                logger.error("Error publishing event: $event", ex)
            }
        }
    }
}
