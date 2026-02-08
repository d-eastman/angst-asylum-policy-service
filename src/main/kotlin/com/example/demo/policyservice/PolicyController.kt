package com.example.demo.policyservice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.slf4j.LoggerFactory

@RestController
@RequestMapping("/policy")
class PolicyController(
    private val service: PolicyService
) {

    private val logger = LoggerFactory.getLogger(PolicyController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPolicy(
        @RequestBody request: CreatePolicyRequest
    ): CreatePolicyResponse {
        logger.info("Creating policy with name={}", request.name)

        val policy = service.createPolicy(request)

        logger.info(
            "Policy created successfully id={} name={}",
            policy.id,
            policy.name
        )

        return policy
    }

    @GetMapping("/ping")
    fun ping(): ResponseEntity<String> = ResponseEntity.ok("pong")

    @GetMapping("/summary")
    fun getSummary(): ResponseEntity<List<PolicyStatusCount>> = ResponseEntity.ok(service.getSummary())

}
