package com.example.demo.policyservice

import org.springframework.stereotype.Service
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import tools.jackson.databind.ObjectMapper

@Service
class PolicyService(
    private val repository: PolicyRepository,
    private val rabbitTemplate: RabbitTemplate,
    private val outboxRepository: OutboxEventRepository,
    private val objectMapper: ObjectMapper
) {
    fun createPolicy(request: CreatePolicyRequest): CreatePolicyResponse {
        val saved = repository.save(
            Policy(name = request.name)
        )

        val event = PolicyCreatedEvent(
            id = requireNotNull(saved.id),
            name = saved.name,
            status = saved.status.toString(),
        )

        outboxRepository.save(
            OutboxEvent(
                aggregateType = "Policy",
                aggregateId = event.id,
                eventType = "PolicyCreated",
                payload = event
            )
        )

        return CreatePolicyResponse(
            id = requireNotNull(saved.id),
            name = saved.name,
            status = saved.status.toString(),
        )
    }

    fun getSummary(): List<PolicyStatusCount> =
        repository.countPoliciesByStatus()
}
