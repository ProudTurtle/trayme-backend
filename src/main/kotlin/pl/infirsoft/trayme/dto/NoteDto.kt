package pl.infirsoft.trayme.dto

import java.time.LocalDateTime

data class NoteDto(
    val id: Int,
    val title: String,
    val updatedAt: LocalDateTime,
    val content: String
)