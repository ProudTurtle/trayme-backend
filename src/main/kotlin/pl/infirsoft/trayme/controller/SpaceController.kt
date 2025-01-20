package pl.infirsoft.trayme.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import pl.infirsoft.trayme.aspect.RequiresUser
import pl.infirsoft.trayme.dto.ShareKeyDto
import pl.infirsoft.trayme.dto.SpaceDto
import pl.infirsoft.trayme.payload.SpacePayload
import pl.infirsoft.trayme.payload.SpaceUpdatePayload
import pl.infirsoft.trayme.repository.UserRepository
import pl.infirsoft.trayme.service.SpaceService

@RestController
@RequestMapping("/spaces")
class SpaceController(
    private val spaceService: SpaceService,
    private val userRepository: UserRepository
) {

    @RequiresUser
    @GetMapping
    @Operation(summary = "Space. Lista", description = "Pobiera listę space dla użytkowników")
    fun index(@RequestHeader("X-User-Token") userPassword: String): List<SpaceDto> {
        val user = userRepository.requireBy(userPassword)
        return spaceService.getAllSpaces(userPassword).map { it.toDto(user) }
    }

    @PostMapping
    @Operation(summary = "Space.Tworzenie", description = "Tworzy nowego space")
    fun createSpace(
        @RequestHeader("X-User-Token") userPassword: String,
        @RequestBody spacePayload: SpacePayload
    ): SpaceDto {
        val user = userRepository.requireBy(userPassword)
        return spaceService.createSpace(spacePayload, userPassword)
    }

    @DeleteMapping("{spacesId}")
    @Operation(summary = "Spaces.Usuwanie", description = "Usuwa wskazanego space")
    fun deleteSpace(@PathVariable spacesId: Int, @RequestHeader("X-User-Token") userPassword: String) {
        spaceService.deleteSpace(spacesId, userPassword)
    }

    @RequiresUser
    @PutMapping("{spacesId}")
    @Operation(summary = "Spaces.Aktualizacja", description = "Aktualizuje spaces")
    fun updateSpace(
        @RequestHeader("X-User-Token") userPassword: String,
        @RequestBody spacePayload: SpaceUpdatePayload,
        @PathVariable spacesId: Int,
    ): SpaceDto {
        val user = userRepository.requireBy(userPassword)
        return spaceService.updateSpace(spacePayload, spacesId, userPassword).toDto(user)
    }

    @PostMapping("share")
    @Operation(summary = "Spaces.Sharowanie", description = "Udostępnia swojego space innemu użytkownikowi")
    fun getSpaces(
        @RequestHeader("X-User-Token") userPassword: String,
        @RequestParam shareKey: String
    ): SpaceDto {
        val user = userRepository.requireBy(userPassword)
        return spaceService.shareSpace(userPassword, shareKey).toDto(user)
    }

    @RequiresUser
    @PostMapping("{spacesId}/generate-share-key")
    @Operation(summary = "Spaces.Sharowanie", description = "Tworzy share key")
    fun generateShareKey(
        @RequestHeader("X-User-Token") userPassword: String,
        @PathVariable spacesId: Int
    ): ShareKeyDto {
        return spaceService.generateShareKey(spacesId)
    }

    @RequiresUser
    @GetMapping("{spacesId}/share-key")
    @Operation(summary = "Spaces.Sharowanie", description = "Udostępnia shareKey")
    fun getShareKey(
        @RequestHeader("X-User-Token") userPassword: String,
        @PathVariable spacesId: Int
    ): ShareKeyDto {
        return spaceService.getShareKey(spacesId)
    }

    @DeleteMapping("{spacesId}/leave-space")
    @Operation(summary = "Spaces. Opuszczanie", description = "Opuszczanie spaces jako member. Nie usuwa spaces")
    fun leaveSpace(
        @RequestHeader("X-User-Token") userPassword: String,
        @PathVariable spacesId: Int
    ) {
        spaceService.leaveSpace(userPassword, spacesId)
    }
}
