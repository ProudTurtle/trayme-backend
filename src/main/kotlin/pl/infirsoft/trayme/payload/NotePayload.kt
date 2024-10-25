package pl.infirsoft.trayme.payload;

import pl.infirsoft.trayme.domain.Note
import java.time.LocalDateTime

data class NotePayload(
    val title: String,
    val content: String
) {
    fun toEntity(): Note {
        return Note(title, LocalDateTime.now(), content)
    }
}
