package pl.infirsoft.trayme.payload;

import pl.infirsoft.trayme.domain.Note
import pl.infirsoft.trayme.domain.Space
import java.time.LocalDateTime

data class NotePayload(
    val title: String?,
    val content: String?,

    ) {
    fun toEntity(space: Space): Note {
        return Note(title!!, LocalDateTime.now(), content!!, space)
    }
}