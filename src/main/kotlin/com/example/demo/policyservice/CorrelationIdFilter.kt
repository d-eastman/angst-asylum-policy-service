package com.example.demo.policyservice

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID

@Component
class CorrelationIdFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val traceId = UUID.randomUUID().toString()
        MDC.put("traceId", traceId)
        response.addHeader("X-Trace-Id", traceId)
        response.addHeader("correlation-id", request.getHeader("correlation-id") ?: "unknown")

        filterChain.doFilter(request, response)

        MDC.clear()
    }
}
