package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.Note
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.exception.ModuleNotFoundException
import pl.infirsoft.trayme.exception.NoteServiceException
import pl.infirsoft.trayme.exception.UserNotFoundException
import pl.infirsoft.trayme.payload.NotePayload
import pl.infirsoft.trayme.repository.ModuleRepository
import pl.infirsoft.trayme.repository.NoteRepository
import pl.infirsoft.trayme.repository.SpaceRepository
import pl.infirsoft.trayme.repository.UserRepository

@Service
class NoteService(
    private val noteRepository: NoteRepository,
    private val spaceRepository: SpaceRepository,
    private val userRepository: UserRepository,
    private val moduleRepository: ModuleRepository
) {

    fun createNote(payload: NotePayload, userPassword: String): Note {
        return try {
            val user = userRepository.requireBy(userPassword)
            val module = moduleRepository.requireBy(1)
            val note = noteRepository.save(payload.toEntity())
            val space = Space(module, user, note)
            spaceRepository.save(space)
            note
        } catch (e: UserNotFoundException) {
            throw NoteServiceException("User not found", e)
        } catch (e: ModuleNotFoundException) {
            throw NoteServiceException("Module not found", e)
        }
    }

    fun getNotes(userPassword: String): List<Note> {
        return noteRepository.findNotesByUserPassword(userPassword)
    }

    fun updateNote(payload: NotePayload, noteId: Int): Note {
        val note = noteRepository.requireBy(noteId)

        payload.title.let { note.setTitle(it) }
        payload.content.let { note.setContent(it) }
        return noteRepository.save(note)
    }

    fun deleteNote(noteId: Int, userPassword: String) {
        try {
            val user = userRepository.requireBy(userPassword)
            noteRepository.deleteById(noteId)
        } catch (e: UserNotFoundException) {
            throw NoteServiceException("User not found", e)
        }
    }
}