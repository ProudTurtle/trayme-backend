package pl.infirsoft.trayme.domain

import jakarta.persistence.Entity
import jakarta.persistence.Table
import pl.infirsoft.trayme.dto.NoteDto
import java.time.LocalDateTime

@Entity
@Table(name = "note")
class Note(
    private var title: String,
    private var updatedAt: LocalDateTime,
    private var content: String,
    space: Space
) : Content(space = space) {

    override fun toDto(): NoteDto {
        return NoteDto(id ?: 0, title, updatedAt, content)
    }

    fun setTitle(newTitle: String) {
        this.title = newTitle
    }

    fun setContent(newContent: String) {
        this.content = newContent
    }

    fun setUpdateAt(newUpdateAt: LocalDateTime) {
        this.updatedAt = newUpdateAt
    }
}