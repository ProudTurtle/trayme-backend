package pl.infirsoft.trayme.payload

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import pl.infirsoft.trayme.domain.ContactMessages

data class ContactMessagePayload(

    @field:Email(message = "Please provide a valid email address")
    @field:NotBlank(message = "Email cannot be empty")
    @field:Size(max = 64, message = "Email must not exceed 64 characters")
    val email: String,

    @field:NotBlank(message = "Topic cannot be empty")
    @field:Size(max = 64, message = "Topic must not exceed 64 characters")
    val topic: String,

    @field:NotBlank(message = "Message content cannot be empty")
    @field:Size(max = 2048, message = "Message content must not exceed 2048 characters")
    val message: String
) {
    fun toEntity(): ContactMessages {
        return ContactMessages(email, topic, message)
    }
}