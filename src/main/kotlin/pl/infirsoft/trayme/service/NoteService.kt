package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.Note
import pl.infirsoft.trayme.exception.NoteNotFoundException
import pl.infirsoft.trayme.exception.NoteServiceException
import pl.infirsoft.trayme.exception.SpaceNotFoundException
import pl.infirsoft.trayme.payload.NotePayload
import pl.infirsoft.trayme.payload.NoteUpdatePayload
import pl.infirsoft.trayme.repository.NoteRepository
import pl.infirsoft.trayme.repository.SpaceRepository
import java.time.LocalDateTime

@Service
class NoteService(
    private val noteRepository: NoteRepository,
    private val spaceRepository: SpaceRepository
) {

    fun createNote(payload: NotePayload, email: String): Note {
        return try {
            val space = spaceRepository.requireByIdIdAnsUserEmail(payload.spaceId, email)
            val note = noteRepository.save(payload.toEntity(space))
            spaceRepository.save(space)
            note
        } catch (e: SpaceNotFoundException) {
            throw NoteServiceException("Space not found", e)
        }
    }

    fun getNotes(email: String, spaceId: Int): List<Note> {
        return noteRepository.findNotesByUserEmailAndSpaceId(email, spaceId)
    }

    fun updateNote(email: String, payload: NoteUpdatePayload, noteId: Int): Note {
        val note = noteRepository.requireBy(email, noteId)

        payload.title?.let { note.setTitle(it) }
        payload.content?.let { note.setContent(it) }
        note.setUpdateAt(LocalDateTime.now())
        return noteRepository.save(note)
    }

    fun deleteNote(noteId: Int, email: String) {
        try {
            val note = noteRepository.requireBy(email, noteId)
            noteRepository.delete(note)
        } catch (e: NoteNotFoundException) {
            throw NoteServiceException("Note not found", e)
        }
    }
}