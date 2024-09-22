package pl.infirsoft.trayme.dto

import pl.infirsoft.trayme.domain.Space
import java.time.LocalDateTime

data class NoteDto (
    val id : Int,
    val title : String,
    val updateAt: LocalDateTime,
    val content : String,
    val space: Space
)