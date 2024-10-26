package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.exception.SpaceNotFoundException
import pl.infirsoft.trayme.payload.SpacePayload
import pl.infirsoft.trayme.payload.SpaceUpdatePayload
import pl.infirsoft.trayme.repository.ModuleRepository
import pl.infirsoft.trayme.repository.SpaceRepository
import pl.infirsoft.trayme.repository.UserRepository

@Service
class SpaceService(
    private val repository: SpaceRepository,
    private val userRepository: UserRepository,
    private val moduleRepository: ModuleRepository
) {
    fun getAllSpaces(userPassword: String): List<Space> {
        return repository.findByUserPassword(userPassword)
    }

    fun createSpace(spacePayload: SpacePayload, userPassword: String): Space {
        val user = userRepository.requireBy(userPassword)
        val module = moduleRepository.requireBy(spacePayload.moduleId)
        val space = Space(module, user, spacePayload.name)
        return repository.save(space)
    }

    fun updateSpace(payload: SpaceUpdatePayload, spaceId: Int): Space {
        val space = repository.requireBy(spaceId)

        payload.name.let { space.setName(it) }
        return repository.save(space)
    }

    fun deleteSpace(spaceId: Int, userPassword: String) {
        try {
            val space = repository.requireByIdIdAnsUserPassword(spaceId, userPassword)
            repository.delete(space)
        } catch (e: SpaceNotFoundException) {
            throw SpaceNotFoundException(spaceId)
        }
    }
}