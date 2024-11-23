package pl.infirsoft.trayme.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import pl.infirsoft.trayme.payload.ContactMessagePayload
import pl.infirsoft.trayme.service.ContactMessageService


@RestController
@RequestMapping("contact")
class ContactMessageController(
    private var service: ContactMessageService
) {
    @PostMapping
    @Operation(summary = "Contact message. Tworzenie", description = "Tworzy nową wiadomość")
    fun sendMessage(
        @Valid @RequestBody messages: ContactMessagePayload
    ): Int {
        return service.sendMessage(messages)
    }
}
