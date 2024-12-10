package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.domain.UserSpace
import pl.infirsoft.trayme.dto.ShareKeyDto
import pl.infirsoft.trayme.exception.NoPermissions
import pl.infirsoft.trayme.exception.ShareKeyExpiredException
import pl.infirsoft.trayme.exception.SpaceAlreadyAssignedException
import pl.infirsoft.trayme.exception.SpaceNotFoundException
import pl.infirsoft.trayme.payload.SpacePayload
import pl.infirsoft.trayme.payload.SpaceUpdatePayload
import pl.infirsoft.trayme.repository.ModuleRepository
import pl.infirsoft.trayme.repository.SpaceRepository
import pl.infirsoft.trayme.repository.UserRepository
import pl.infirsoft.trayme.repository.UserSpaceRepository
import java.security.SecureRandom
import java.time.LocalDateTime

@Service
class SpaceService(
    private val repository: SpaceRepository,
    private val userRepository: UserRepository,
    private val moduleRepository: ModuleRepository,
    private val userSpaceRepository: UserSpaceRepository
) {
    fun generateShareKey(spaceId: Int): ShareKeyDto {
        val space = repository.requireBy(spaceId)
        val secureRandom = SecureRandom()

        val password = (secureRandom.nextInt(900000) + 100000).toString()

        val expiredTime = LocalDateTime.now().plusMinutes(2)
        space.setShareKey(password)
        space.setShareKeyExpiredAt(expiredTime)
        repository.save(space)
        return ShareKeyDto(password, expiredTime)
    }

    fun getShareKey(spaceId: Int): ShareKeyDto {
        val space = repository.requireBy(spaceId)
        return ShareKeyDto(space.getShareKey(), space.getShareKeyExpiredAt())
    }

    fun getAllSpaces(userPassword: String): List<Space> {
        return repository.findByUserPassword(userPassword)
    }

    fun createSpace(spacePayload: SpacePayload, userPassword: String): Space {
        val user = userRepository.requireBy(userPassword)
        val module = moduleRepository.requireBy(spacePayload.moduleId)
        val space = Space(module, spacePayload.name, null)
        repository.save(space)
        val userSpace = UserSpace(user, space, "Owner")
        userSpaceRepository.save(userSpace)
        return space
    }

    fun updateSpace(payload: SpaceUpdatePayload, spaceId: Int, userPassword: String): Space {
        val space = repository.requireBy(spaceId)

        payload.name.let { space.setName(it) }
        return repository.save(space)
    }

    fun deleteSpace(spaceId: Int, userPassword: String) {
        val space = repository.findByIdAndUserPassword(spaceId, userPassword)
            ?: throw SpaceNotFoundException(spaceId)

        val userSpace = userSpaceRepository.findBySpaceIdAndUserRole(userPassword, spaceId)
            ?: throw NoPermissions()

        if (userSpace.getRole() != "Owner") {
            throw NoPermissions()
        }
        repository.delete(space)
    }

    fun shareSpace(userPassword: String, shareKey: String): Space {
        val user = userRepository.requireBy(userPassword)
        val space = repository.requireByShareKey(shareKey)

        if (userSpaceRepository.checkIfUserSpaceExist(user, space)) {
            throw SpaceAlreadyAssignedException()
        }

        space.getShareKeyExpiredAt()?.let {
            if (!it.isAfter(LocalDateTime.now())) {
                throw ShareKeyExpiredException()
            }
        }

        userSpaceRepository.save(UserSpace(user, space, "Member"))
        return space
    }
}