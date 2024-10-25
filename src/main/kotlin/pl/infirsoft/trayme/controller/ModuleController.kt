package pl.infirsoft.trayme.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.infirsoft.trayme.domain.Module
import pl.infirsoft.trayme.dto.ModuleDto
import pl.infirsoft.trayme.service.ModuleService

@RestController
@RequestMapping("/modules")
class ModuleController(
    private val moduleService: ModuleService
) {
    @GetMapping
    fun index(): List<ModuleDto> {
        return moduleService.getAllModules().map(Module::toDto)
    }
}
