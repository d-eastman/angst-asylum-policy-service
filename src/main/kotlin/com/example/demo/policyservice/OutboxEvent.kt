package com.example.demo.policyservice

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "outbox_event")
class OutboxEvent(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false)
    val aggregateType: String,

    @Column(nullable = false)
    val aggregateId: UUID,

    @Column(nullable = false)
    val eventType: String,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    val payload: PolicyCreatedEvent,

    val createdAt: Instant = Instant.now(),

    var publishedAt: Instant? = null
)
