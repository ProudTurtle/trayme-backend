package pl.infirsoft.trayme.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.infirsoft.trayme.dto.RegisterDto
import pl.infirsoft.trayme.service.RegisterService

@RestController
@RequestMapping("/init")
class InitController(private val registerService: RegisterService) {

    @PostMapping
    fun createUser(
        @RequestHeader("X-User-Token", required = false) userPassword: String?
    ): RegisterDto {
        return registerService.generatePassword(userPassword)
    }
}