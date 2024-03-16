package com.example.requestSerializables

import kotlinx.serialization.Serializable

@Serializable
data class PasswordExistRequest (
    val id: Int
)