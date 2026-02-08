package com.example.demo.policyservice

interface PolicyStatusCount {
    fun getStatus(): PolicyStatus
    fun getCount(): Long
}
