package pl.infirsoft.trayme.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import pl.infirsoft.trayme.aspect.RequiresUser
import pl.infirsoft.trayme.aspect.UserVerificationAspect
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.dto.ShareKeyDto
import pl.infirsoft.trayme.dto.SpaceDto
import pl.infirsoft.trayme.payload.SpacePayload
import pl.infirsoft.trayme.payload.SpaceUpdatePayload
import pl.infirsoft.trayme.service.SpaceService


@RestController
@RequestMapping("/spaces")
class SpaceController(
    private val spaceService: SpaceService
) {

    @RequiresUser
    @GetMapping
    @Operation(summary = "Space. Lista", description = "Pobiera listę space dla użytkowników")
    fun index(): List<SpaceDto> {
        return spaceService.getAllSpaces(UserVerificationAspect.getUserEmail()).map(Space::toDto)
    }

    @RequiresUser
    @PostMapping
    @Operation(summary = "Space.Tworzenie", description = "Tworzy nowego space")
    fun createSpace(
        @RequestBody spacePayload: SpacePayload
    ): SpaceDto {
        return spaceService.createSpace(spacePayload, UserVerificationAspect.getUserEmail()).toDto()
    }

    @RequiresUser
    @DeleteMapping("{spacesId}")
    @Operation(summary = "Spaces.Usuwanie", description = "Usuwa wskazanego space")
    fun deleteSpace(@PathVariable spacesId: Int) {
        spaceService.deleteSpace(spacesId, UserVerificationAspect.getUserEmail())
    }

    @RequiresUser
    @PutMapping("{spacesId}")
    @Operation(summary = "Spaces.Aktualizacja", description = "Aktualizuje spaces")
    fun updateSpace(
        @RequestBody spacePayload: SpaceUpdatePayload,
        @PathVariable spacesId: Int,
    ): SpaceDto {
        return spaceService.updateSpace(spacePayload, spacesId, UserVerificationAspect.getUserEmail()).toDto()
    }

    @RequiresUser
    @PostMapping("share")
    @Operation(summary = "Spaces.Sharowanie", description = "Udostępnia swojego space innemu użytkownikowi")
    fun getSpaces(
        @RequestParam shareKey: String
    ): SpaceDto {
        return spaceService.shareSpace(UserVerificationAspect.getUserEmail(), shareKey).toDto()
    }

    @RequiresUser
    @PostMapping("{spacesId}/generate-share-key")
    @Operation(summary = "Spaces.Sharowanie", description = "Tworzy share key")
    fun generateShareKey(
        @PathVariable spacesId: Int
    ): ShareKeyDto {
        return spaceService.generateShareKey(spacesId)
    }

    @RequiresUser
    @GetMapping("{spacesId}/share-key")
    @Operation(summary = "Spaces.Sharowanie", description = "Udostępnia shareKey")
    fun getShareKey(
        @PathVariable spacesId: Int
    ): ShareKeyDto {
        return spaceService.getShareKey(spacesId)
    }
}
