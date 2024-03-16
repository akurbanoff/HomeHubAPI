package com.example.responseSerializables

import kotlinx.serialization.Serializable

@Serializable
data class PasswordExistResponse(
    val isExist: Boolean
)
