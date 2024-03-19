package com.example.requestSerializables

import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class NewsRequest(
    val title: String,
    val description: String,
    val photos: List<String>
)
