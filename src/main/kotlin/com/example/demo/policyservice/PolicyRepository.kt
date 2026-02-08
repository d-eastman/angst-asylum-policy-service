package com.example.demo.policyservice

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface PolicyRepository : JpaRepository<Policy, UUID> {
    @Query("""
        select p.status as status, count(p) as count
        from Policy p
        group by p.status
        order by p.status
    """)
    fun countPoliciesByStatus(): List<PolicyStatusCount>
}
