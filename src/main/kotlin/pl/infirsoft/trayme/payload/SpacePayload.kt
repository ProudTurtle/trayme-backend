package pl.infirsoft.trayme.payload;

import pl.infirsoft.trayme.domain.Module
import pl.infirsoft.trayme.domain.Note
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.domain.User
import java.time.LocalDateTime

data class SpacePayload(
    val name: String,
    val moduleId: Int
) {
    fun toEntity(user: User, module: Module): Space {
        return Space(module, user, name)
    }
}

data class SpaceUpdatePayload(
    val name: String
) {
    fun toEntity(user: User, module: Module): Space {
        return Space(module, user, name)
    }
}