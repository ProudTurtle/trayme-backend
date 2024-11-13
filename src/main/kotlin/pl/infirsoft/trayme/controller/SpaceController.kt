package pl.infirsoft.trayme.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.dto.SpaceDto
import pl.infirsoft.trayme.payload.SpacePayload
import pl.infirsoft.trayme.payload.SpaceUpdatePayload
import pl.infirsoft.trayme.service.SpaceService

@RestController
@RequestMapping("/spaces")
class SpaceController(
    private val spaceService: SpaceService
) {

    @GetMapping
    @Operation(summary = "Space. Lista", description = "Pobiera listę space dla użytkowników")
    fun index(@RequestHeader("X-User-Token") userPassword: String): List<SpaceDto> {
        return spaceService.getAllSpaces(userPassword).map(Space::toDto)
    }

    @PostMapping
    @Operation(summary = "Space.Tworzenie", description = "Tworzy nowego space")
    fun createNote(
        @RequestHeader("X-User-Token") userPassword: String,
        @RequestBody spacePayload: SpacePayload
    ): SpaceDto {
        return spaceService.createSpace(spacePayload, userPassword).toDto()
    }

    @DeleteMapping("{spacesId}")
    @Operation(summary = "Spaces.Usuwanie", description = "Usuwa wskazanego space")
    fun deleteNote(@PathVariable spacesId: Int, @RequestHeader("X-User-Token") userPassword: String) {
        spaceService.deleteSpace(spacesId, userPassword)
    }

    @PutMapping("{spacesId}")
    @Operation(summary = "Spaces.Aktualizacja", description = "Aktualizuje spaces")
    fun updateNote(@RequestBody spacePayload: SpaceUpdatePayload, @PathVariable spacesId: Int): SpaceDto {
        return spaceService.updateSpace(spacePayload, spacesId).toDto()
    }
//
//    @PostMapping
//    @Operation(summary = "Space. Dodawanie space od innego użytkownika", description = "Dodajemy do swojej przestrzeni space od innego użytkownika")

}
