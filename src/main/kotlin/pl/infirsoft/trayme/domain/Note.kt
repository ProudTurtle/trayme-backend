package pl.infirsoft.trayme.domain

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import pl.infirsoft.trayme.dto.NoteDto
import java.time.LocalDateTime

@Entity
@DiscriminatorValue("note")
class Note(
    private var title: String,
    private var updateAt: LocalDateTime,
    private var content: String,
) : Content() {

    override fun toDto(): NoteDto {
        return NoteDto(id ?: 0, title, updateAt, content)
    }

    fun setTitle(newTitle: String) {
        this.title = newTitle
    }

    fun setContent(newContent: String) {
        this.content = newContent
    }
}