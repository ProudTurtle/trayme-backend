package pl.infirsoft.trayme.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import pl.infirsoft.trayme.domain.Note
import pl.infirsoft.trayme.dto.NoteDto
import pl.infirsoft.trayme.payload.NotePayload
import pl.infirsoft.trayme.payload.NoteUpdatePayload
import pl.infirsoft.trayme.service.NoteService


@RestController
@RequestMapping("{spaceId}/notes")
class NoteController(
    private val noteService: NoteService
) {
    @GetMapping
    @Operation(summary = "Notatki.Lista", description = "Zwraca listę notatek dla danego userPassword i spaceId")
    fun getNotes(@RequestHeader("X-User-Token") userPassword: String,
                 @PathVariable spaceId: Int): List<NoteDto> {
        return noteService.getNotes(userPassword, spaceId).map(Note::toDto)
    }

    @PostMapping
    @Operation(summary = "Notatki.Tworzenie", description = "Tworzy nową notatkę")
    fun createNote(
        @RequestHeader("X-User-Token") userPassword: String,
        @RequestBody notePayload: NotePayload
    ): NoteDto {
        return noteService.createNote(notePayload, userPassword).toDto()
    }

    @PatchMapping("{noteId}")
    @Operation(summary = "Notatki.Aktualizacja", description = "Aktualizuje notatkę")
    fun updateNote(
        @RequestHeader("X-User-Token") userPassword: String,
        @RequestBody notePayload: NoteUpdatePayload,
        @PathVariable noteId: Int
    ): NoteDto {
        return noteService.updateNote(userPassword, notePayload, noteId).toDto()
    }

    @DeleteMapping("{noteId}")
    @Operation(summary = "Notatki.Usuwanie", description = "Usuwa wskazaną notatkę")
    fun deleteNote(@PathVariable noteId: Int, @RequestHeader("X-User-Token") userPassword: String) {
        noteService.deleteNote(noteId, userPassword)
    }
}
