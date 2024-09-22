package pl.infirsoft.trayme.controller

import org.springframework.web.bind.annotation.*
import pl.infirsoft.trayme.dto.NoteDto
import pl.infirsoft.trayme.payload.NotePayload
import pl.infirsoft.trayme.service.NoteService

@RestController
@RequestMapping("/note")
class NoteController(
    private val noteService: NoteService
) {

    @PostMapping("/create")
    fun createNote(@RequestBody notePayload: NotePayload): NoteDto {
        return noteService.createNote(notePayload).toDto()
    }

    @PutMapping("{noteId}/update")
    fun updateNote(@RequestBody notePayload: NotePayload, @PathVariable noteId: Int): NoteDto {
        return noteService.updateNote(notePayload, noteId).toDto()
    }
}
