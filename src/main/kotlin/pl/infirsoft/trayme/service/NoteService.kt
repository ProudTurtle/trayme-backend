package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.Note
import pl.infirsoft.trayme.payload.NotePayload
import pl.infirsoft.trayme.repository.NoteRepository
import pl.infirsoft.trayme.repository.SpaceRepository

@Service
class NoteService(
    private val noteRepository: NoteRepository,
    private val spaceRepository: SpaceRepository
) {

    fun createNote(payload: NotePayload): Note {
        val space = spaceRepository.requireBy(1)
        return noteRepository.save(payload.toEntity(space))
    }

    fun updateNote(payload: NotePayload, noteId: Int): Note {
        val space = spaceRepository.requireBy(1)
        val note = noteRepository.requireBy(noteId)

        payload.title?.let { note.setTitle(it) }
        payload.content?.let { note.setContent(it) }
        return noteRepository.save(payload.toEntity(space))
    }
}