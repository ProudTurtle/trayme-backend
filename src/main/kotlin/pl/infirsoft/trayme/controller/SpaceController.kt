package pl.infirsoft.trayme.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
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
    fun index(@RequestHeader userPassword: String): List<SpaceDto> {
        return spaceService.getAllSpaces(userPassword).map(Space::toDto)
    }
//
//    @GetMapping("/{spaceId}/entities")
//    fun getEntities(@PathVariable spaceId: Int): List<*> {
//        return spaceService.getByEntitiesBy(spaceId)
//    }
}
