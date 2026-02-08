package com.example.demo.policyservice

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class DatabaseStartupCheck(
    private val dataSource: DataSource
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        dataSource.connection.use { conn ->
            if (!conn.isValid(2)) {
                throw IllegalStateException("Database connection is not valid")
            }
        }
        println("✅ Database connection successful")
    }
}
