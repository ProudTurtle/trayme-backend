package pl.infirsoft.trayme.controller

import org.springframework.web.bind.annotation.*
import pl.infirsoft.trayme.domain.Note
import pl.infirsoft.trayme.dto.NoteDto
import pl.infirsoft.trayme.payload.NotePayload
import pl.infirsoft.trayme.service.NoteService

@RestController
@RequestMapping("/note")
class NoteController(
    private val noteService: NoteService
) {
    @GetMapping
    fun getNotes(@RequestHeader userPassword: String): List<NoteDto> {
        return noteService.getNotes(userPassword).map(Note::toDto)
    }

    @PostMapping("/create")
    fun createNote(@RequestHeader userPassword: String, @RequestBody notePayload: NotePayload): NoteDto {
        return noteService.createNote(notePayload, userPassword).toDto()
    }

    @PutMapping("{noteId}/update")
    fun updateNote(@RequestBody notePayload: NotePayload, @PathVariable noteId: Int): NoteDto {
        return noteService.updateNote(notePayload, noteId).toDto()
    }
}
