package pl.infirsoft.trayme.dto

import java.time.LocalDateTime

data class ContentDto(
    val id: Int,
    val title: String?,
    val updateAt: LocalDateTime?,
    val content: String?,
    val name: String?,
    val who: String?,
    val type: String?,
    val contentType: String?
)
