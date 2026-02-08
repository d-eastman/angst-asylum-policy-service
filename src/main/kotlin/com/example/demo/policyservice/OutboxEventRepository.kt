package com.example.demo.policyservice

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import jakarta.persistence.LockModeType
import java.util.UUID

interface OutboxEventRepository : JpaRepository<OutboxEvent, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(
        """
        select e from OutboxEvent e
        where e.publishedAt is null
        order by e.createdAt
        """
    )
    fun findUnpublished(): List<OutboxEvent>
}
