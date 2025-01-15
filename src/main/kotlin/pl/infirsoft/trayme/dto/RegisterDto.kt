package pl.infirsoft.trayme.dto

import pl.infirsoft.trayme.domain.Space


data class RegisterDto(
    val token: String,
    val name: String,
    val email: String?,
    val avatarUrl: String?,
    val spaces: List<Space>
)