package pl.infirsoft.trayme.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class SpaceAlreadyAssignedException(message: String) : RuntimeException("Space jest już przypisany do konta")