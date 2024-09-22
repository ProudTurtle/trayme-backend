package pl.infirsoft.trayme.domain

import jakarta.persistence.*
import pl.infirsoft.trayme.dto.NoteDto
import java.time.LocalDateTime

@Entity
@Table(name = "note")
class Note(
    private var title: String,
    private var updateAt: LocalDateTime,
    private var content: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    val space: Space
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    fun toDto(): NoteDto {
        return NoteDto(id ?: 0, title, updateAt, content, space)
    }

    fun setTitle(newTitle: String) {
        this.title = newTitle
    }

    fun setContent(newContent: String) {
        this.content = newContent
    }
}