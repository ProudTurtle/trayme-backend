package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Note

interface NoteCustomRepository {
    fun requireBy(userPassword: String, id: Int): Note
    fun findNotesByUserPasswordAndSpaceId(userPassword: String, spaceId: Int): List<Note>
    fun findNoteByUserPasswordAndNoteId(userPassword: String, noteId: Int): Note?
}