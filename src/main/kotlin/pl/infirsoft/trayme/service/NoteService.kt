package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.Note
import pl.infirsoft.trayme.dto.NoteDto
import pl.infirsoft.trayme.repository.NoteRepository

@Service
class NoteService(private val noteRepository: NoteRepository) {

    fun findBy(id: Int): List<Note> {
        return noteRepository.findBy(id)
    }
}