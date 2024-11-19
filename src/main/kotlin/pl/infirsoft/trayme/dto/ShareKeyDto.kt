package pl.infirsoft.trayme.dto

import java.time.LocalDateTime

data class ShareKeyDto(
    val shareKey: String?,
    val shareKeyExpiredAt: LocalDateTime?
)
