package com.example.demo.policyservice

import jakarta.persistence.*
import java.util.UUID

enum class PolicyStatus {
    INSERTED,
    CREATED,
    PURCHASED,
    CANCELLED,
    EXPIRED,
}

@Entity
@Table(name = "policy")
class Policy(
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    val id: UUID? = null,

    @Column(nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: PolicyStatus = PolicyStatus.INSERTED,
)
