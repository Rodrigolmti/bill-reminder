package com.vortex.billreminder.domain.model

import java.util.*

data class Bill(
    val id: Int? = null,
    val value: Double?,
    val description: String?,
    val date: Date = Date()
)