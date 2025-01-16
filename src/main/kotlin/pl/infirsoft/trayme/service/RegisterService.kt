package pl.infirsoft.trayme.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.User
import pl.infirsoft.trayme.dto.RegisterDto
import pl.infirsoft.trayme.payload.NotePayload
import pl.infirsoft.trayme.payload.SpacePayload
import pl.infirsoft.trayme.repository.UserRepository
import java.security.SecureRandom

@Service
class RegisterService(
    private val userRepository: UserRepository,
    private val noteService: NoteService,
    private val spaceService: SpaceService
) {
    @Transactional
    fun generatePassword(userPassword: String?): RegisterDto {
        val existingUser = userPassword?.let {
            runCatching { userRepository.requireBy(it) }.getOrNull()
        }

        if (existingUser != null) {
            val spaces = spaceService.getAllSpaces(existingUser.getPassword())
            val spacesDTO = spaces.map { it.toDto(existingUser) }
            return RegisterDto(existingUser.getPassword(), "Guest", null, null, spacesDTO)
        }

        val charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#\$%^&*()"
        val secureRandom = SecureRandom()

        val password = (5..12)
            .map { secureRandom.nextInt(charPool.length) }
            .map(charPool::get)
            .joinToString("")

        val user = User(password)

        userRepository.save(user)
        val spacePayload = SpacePayload("Notes", 1)
        val space = spaceService.createSpace(spacePayload, password)
        val payload = NotePayload(
            "Welcome to Trayme! 👋",
            "We're always glad to see a new user 😊\n" +
                    "Go ahead, explore features and optimize your life 🚀\n" +
                    "\n" +
                    "Enjoy using the app!", space.id
        )
        noteService.createNote(payload, password)
        val spaces = spaceService.getAllSpaces(password)
        spaces.forEach { spaceService.refreshSpace(it) }
        val spacesDTO = spaces.map { it.toDto(user) }
        return RegisterDto(password, "Guest", null, null, spacesDTO)
    }
}