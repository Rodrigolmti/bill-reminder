package com.vortex.billreminder.domain.model

import java.util.*

data class Bill(
    val value: Double,
    val description: String,
    val date: Date,
    val categoryId: Int
)