package pl.infirsoft.trayme.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class SpaceNotFoundException(id: Int? = null, shareKey: String? = null) : RuntimeException(
    when {
        id != null -> "Space with id $id not found"
        shareKey != null -> "Space with shareKey $shareKey not found"
        else -> "Space not found"
    }
)
