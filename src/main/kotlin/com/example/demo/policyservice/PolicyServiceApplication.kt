package com.example.demo.policyservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class PolicyServiceApplication

fun main(args: Array<String>) {
	runApplication<PolicyServiceApplication>(*args)
}
