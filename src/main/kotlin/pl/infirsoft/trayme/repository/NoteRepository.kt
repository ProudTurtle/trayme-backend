package pl.infirsoft.trayme.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.infirsoft.trayme.domain.Note

interface NoteRepository : JpaRepository<Note, Int>, NoteCustomRepository