package pl.infirsoft.trayme.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class UserNotFoundException(email: String) : RuntimeException("User with email $email not found")