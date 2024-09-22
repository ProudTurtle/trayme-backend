package pl.infirsoft.trayme.repository

import org.springframework.stereotype.Repository
import pl.infirsoft.trayme.domain.Note

interface NoteCustomRepository{
    fun findBy(id: Int): List<Note>
}