package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.domain.UserSpace
import pl.infirsoft.trayme.exception.SpaceAlreadyAssignedException
import pl.infirsoft.trayme.exception.SpaceNotFoundException
import pl.infirsoft.trayme.payload.SpacePayload
import pl.infirsoft.trayme.payload.SpaceUpdatePayload
import pl.infirsoft.trayme.repository.ModuleRepository
import pl.infirsoft.trayme.repository.SpaceRepository
import pl.infirsoft.trayme.repository.UserRepository
import pl.infirsoft.trayme.repository.UserSpaceRepository
import java.security.SecureRandom

@Service
class SpaceService(
    private val repository: SpaceRepository,
    private val userRepository: UserRepository,
    private val moduleRepository: ModuleRepository,
    private val userSpaceRepository: UserSpaceRepository
) {
    fun generateShareKey(): String {
        val charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#^&*()"
        val secureRandom = SecureRandom()

        val password = (5..12)
            .map { secureRandom.nextInt(charPool.length) }
            .map(charPool::get)
            .joinToString("")

        return password
    }

    fun getAllSpaces(userPassword: String): List<Space> {
        return repository.findByUserPassword(userPassword)
    }

    fun createSpace(spacePayload: SpacePayload, userPassword: String): Space {
        val user = userRepository.requireBy(userPassword)
        val module = moduleRepository.requireBy(spacePayload.moduleId)
        val space = Space(module, spacePayload.name, generateShareKey())
        repository.save(space)
        val userSpace = UserSpace(user, space, "Test")
        userSpaceRepository.save(userSpace)
        return space
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

    fun shareSpace(userPassword: String, shareKey: String): Space {
        val user = userRepository.requireBy(userPassword)
        val space = repository.requireByShareKey(shareKey)
        if (userSpaceRepository.checkIfUserSpaceExist(user, space)) {
            throw SpaceAlreadyAssignedException("Space jest już przypisany do konta")
        }
        val userSpace = UserSpace(user, space, "Test")
        userSpaceRepository.save(userSpace)
        return space
    }
}