package pl.infirsoft.trayme.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class UserNotFoundException(userPassword: String) : RuntimeException("User with id $userPassword not found")