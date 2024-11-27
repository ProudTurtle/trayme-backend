package pl.infirsoft.trayme.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.infirsoft.trayme.aspect.RequiresUser
import pl.infirsoft.trayme.aspect.UserVerificationAspect
import pl.infirsoft.trayme.service.RegisterService

@RestController
@RequestMapping("/notes")
class RegistrationController(val registerService: RegisterService) {

    @RequiresUser
    @PostMapping
    fun createUser(): String {
        val email = UserVerificationAspect.getUserEmail()
        return registerService.createUser(email)
    }

}