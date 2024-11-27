package pl.infirsoft.trayme.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import pl.infirsoft.trayme.aspect.RequiresUser
import pl.infirsoft.trayme.aspect.UserVerificationAspect
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

    @RequiresUser
    @GetMapping
    @Operation(summary = "Notatki.Lista", description = "Zwraca listę notatek dla danego emaila i spaceId")
    fun getNotes(
        @PathVariable spaceId: Int
    ): List<NoteDto> {
        return noteService.getNotes(UserVerificationAspect.getUserEmail(), spaceId).map(Note::toDto)
    }

    @RequiresUser
    @PostMapping
    @Operation(summary = "Notatki.Tworzenie", description = "Tworzy nową notatkę")
    fun createNote(
        @RequestBody notePayload: NotePayload
    ): NoteDto {
        return noteService.createNote(notePayload, UserVerificationAspect.getUserEmail()).toDto()
    }

    @RequiresUser
    @PutMapping("{noteId}")
    @Operation(summary = "Notatki.Aktualizacja", description = "Aktualizuje notatkę")
    fun updateNote(
        @RequestBody notePayload: NoteUpdatePayload,
        @PathVariable noteId: Int
    ): NoteDto {
        return noteService.updateNote(UserVerificationAspect.getUserEmail(), notePayload, noteId).toDto()
    }

    @RequiresUser
    @DeleteMapping("{noteId}")
    @Operation(summary = "Notatki.Usuwanie", description = "Usuwa wskazaną notatkę")
    fun deleteNote(@PathVariable noteId: Int) {
        noteService.deleteNote(noteId, UserVerificationAspect.getUserEmail())
    }
}
