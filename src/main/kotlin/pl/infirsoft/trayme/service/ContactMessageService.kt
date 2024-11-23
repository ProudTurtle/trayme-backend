package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.ContactMessages
import pl.infirsoft.trayme.payload.ContactMessagePayload
import pl.infirsoft.trayme.repository.ContactMessagesRepository
import java.time.LocalDateTime

@Service
class ContactMessageService(
    private val contactMessageRepository: ContactMessagesRepository
) {

    fun sendMessage(payload: ContactMessagePayload): Int {
        val contactMessage = ContactMessages(payload.email, payload.topic, payload.message)
        contactMessage.setCreatedAt(LocalDateTime.now())
        contactMessageRepository.save(contactMessage)

        return contactMessage.id
    }
}