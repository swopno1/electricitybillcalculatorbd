package com.example.domain.model

data class CalculationHistoryItem(
    val id: String,
    val timestamp: Long,
    val units: Double,
    val totalAmount: Double,
    val planNameEn: String,
    val planNameBn: String
)
