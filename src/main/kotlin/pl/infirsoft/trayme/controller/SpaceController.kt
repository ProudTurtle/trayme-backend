package pl.infirsoft.trayme.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.infirsoft.trayme.service.SpaceService

@RestController
@RequestMapping("/spaces")
class SpaceController(
    private val spaceService: SpaceService
) {

//    @GetMapping
//    fun index(@RequestHeader userPassword: String): List<SpaceDto> {
//        return spaceService.getAllSpaces().map(Space::toDto)
//    }
//
//    @GetMapping("/{spaceId}/entities")
//    fun getEntities(@PathVariable spaceId: Int): List<*> {
//        return spaceService.getByEntitiesBy(spaceId)
//    }
}
