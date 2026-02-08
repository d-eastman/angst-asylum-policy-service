package com.example.demo.policyservice

import org.springframework.boot.health.contributor.Health
import org.springframework.boot.health.contributor.HealthIndicator
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class DatabaseHealthIndicator(
    private val dataSource: DataSource
) : HealthIndicator {

    override fun health(): Health {
        return try {
            dataSource.connection.use { conn ->
                if (conn.isValid(2)) {
                    Health.up()
                        .withDetail("database", "reachable")
                        .build()
                } else {
                    Health.down()
                        .withDetail("database", "connection invalid")
                        .build()
                }
            }
        } catch (ex: Exception) {
            Health.down(ex)
                .withDetail("database", "unreachable")
                .build()
        }
    }
}
