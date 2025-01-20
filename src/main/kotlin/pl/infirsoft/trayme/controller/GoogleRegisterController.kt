package pl.infirsoft.trayme.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.infirsoft.trayme.aspect.RequiresUser
import pl.infirsoft.trayme.dto.GoogleAuthRequest
import pl.infirsoft.trayme.dto.RegisterDto
import pl.infirsoft.trayme.service.RegisterService

@RestController
@RequestMapping("/google-oauth")
class GoogleRegisterController(
    private var service: RegisterService
) {

    @RequiresUser
    @PostMapping
    @Operation(summary = "Google. Rejestracja", description = "Rejestruje użytkowników z google auth")
    fun index(
        @RequestHeader("X-User-Token") userPassword: String,
        @RequestBody(required = true) googleAuth: GoogleAuthRequest
    ): RegisterDto {
        return service.registerUserFromGoogle(userPassword, googleAuth.code)
    }
}