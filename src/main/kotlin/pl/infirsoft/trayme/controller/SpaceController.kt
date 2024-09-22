package pl.infirsoft.trayme.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.dto.SpaceDto
import pl.infirsoft.trayme.service.SpaceService

@RestController
@RequestMapping("/spaces")
class SpaceController(
    private val spaceService: SpaceService
) {

    @GetMapping
    fun index(): List<SpaceDto> {
        return spaceService.getAllSpaces().map(Space::toDto)
    }

    @GetMapping("/{spaceId}/entities")
    fun getEntities(@PathVariable spaceId: Int): List<*> {
        return spaceService.getByEntitiesBy(spaceId)
    }
}
