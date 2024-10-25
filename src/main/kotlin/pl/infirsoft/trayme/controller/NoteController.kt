package pl.infirsoft.trayme.controller

import io.swagger.v3.oas.annotations.Operation
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
    @Operation(summary = "Notatki.Lista", description = "Zwraca listę notatek dla danego userPassword")
    fun getNotes(@RequestHeader userPassword: String): List<NoteDto> {
        return noteService.getNotes(userPassword).map(Note::toDto)
    }

    @PostMapping("/create")
    @Operation(summary = "Notatki.Tworzenie", description = "Tworzy nową notatkę")
    fun createNote(@RequestHeader userPassword: String, @RequestBody notePayload: NotePayload): NoteDto {
        return noteService.createNote(notePayload, userPassword).toDto()
    }

    @PutMapping("{noteId}/update")
    @Operation(summary = "Notatki.Aktualizacja", description = "Aktualizuje notatkę")
    fun updateNote(@RequestBody notePayload: NotePayload, @PathVariable noteId: Int): NoteDto {
        return noteService.updateNote(notePayload, noteId).toDto()
    }
}
