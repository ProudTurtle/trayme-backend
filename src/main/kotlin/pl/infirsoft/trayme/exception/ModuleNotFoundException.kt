package pl.infirsoft.trayme.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ModuleNotFoundException(id: Int) : RuntimeException("User with id $id not found")