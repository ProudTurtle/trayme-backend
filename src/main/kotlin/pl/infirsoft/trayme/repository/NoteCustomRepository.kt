package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Note

interface NoteCustomRepository {
    fun requireBy(id: Int): Note
    fun findBy(id: Int): Note?
    fun findNotesByUserPassword(userPassword: String): List<Note>
}