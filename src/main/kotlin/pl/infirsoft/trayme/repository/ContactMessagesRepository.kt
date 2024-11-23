package pl.infirsoft.trayme.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.infirsoft.trayme.domain.ContactMessages

interface ContactMessagesRepository : JpaRepository<ContactMessages, Int>, ContactMessagesCustomRepository