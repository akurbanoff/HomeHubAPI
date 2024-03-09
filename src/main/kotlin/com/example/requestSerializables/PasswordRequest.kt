package com.example.requestSerializables

import kotlinx.serialization.Serializable

@Serializable
data class PasswordRequest(
    val id: Int,
    val password: String
)