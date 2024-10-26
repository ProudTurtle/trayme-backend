package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.Module
import pl.infirsoft.trayme.repository.ModuleRepository


@Service
class ModuleService(
    private val moduleRepository: ModuleRepository
) {
    fun getAllModules(): List<Module> {
        return moduleRepository.findAll()
    }
}