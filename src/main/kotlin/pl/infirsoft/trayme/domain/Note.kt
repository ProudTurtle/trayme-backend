package pl.infirsoft.trayme.domain

import jakarta.persistence.*
import pl.infirsoft.trayme.dto.NoteDto
import java.time.LocalDateTime

@Entity
@Table(name = "note")
class Note(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    private val title: String,
    private val updateAt: LocalDateTime,
    private val content: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    val space: Space
) {
    fun toDto(): NoteDto {
        return NoteDto(id, title, updateAt, content, space)
    }
}