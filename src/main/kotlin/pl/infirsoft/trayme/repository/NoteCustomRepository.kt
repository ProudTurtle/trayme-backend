package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Note

interface NoteCustomRepository {
    fun requireBy(email: String, id: Int): Note
    fun findNotesByUserEmailAndSpaceId(email: String, spaceId: Int): List<Note>
    fun findNoteByUserEmailAndNoteId(email: String, noteId: Int): Note?
}