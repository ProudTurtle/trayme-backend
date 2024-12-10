package pl.infirsoft.trayme.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserSpaceNotFoundException(spaceId: Int? = null) : RuntimeException(
    when {
        spaceId != null -> "Space with space.id $spaceId not found"
        else -> "Space not found"
    }
)
