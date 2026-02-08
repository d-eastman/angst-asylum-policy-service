package com.example.demo.policyservice

import java.util.UUID

data class CreatePolicyResponse(
    val id: UUID,
    val name: String,
    val status: String,
)
