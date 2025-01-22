package pl.infirsoft.trayme.dto

data class RegisterDto(
    val token: String,
    val name: String?,
    val email: String?,
    val avatarUrl: String?,
    val spaces: List<SpaceDto>,
    val modules: List<ModuleDto>
)