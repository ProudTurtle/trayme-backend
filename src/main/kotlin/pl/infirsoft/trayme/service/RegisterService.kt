package pl.infirsoft.trayme.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.User
import pl.infirsoft.trayme.payload.NotePayload
import pl.infirsoft.trayme.payload.SpacePayload
import pl.infirsoft.trayme.repository.UserRepository

@Service
class RegisterService(
    private val userRepository: UserRepository,
    private val noteService: NoteService,
    private val spaceService: SpaceService
) {

    @Transactional
    fun createUser(email: String): String {

        val user = User(email)
        userRepository.save(user)

        val spacePayload = SpacePayload("Notes", 1)
        val space = spaceService.createSpace(spacePayload, email)
        val payload = NotePayload(
            "Welcome to Trayme! 👋",
            "We're always glad to see a new user 😊\n" +
                    "Go ahead, explore features and optimize your life 🚀\n" +
                    "\n" +
                    "Enjoy using the app!", space.id!!
        )
        noteService.createNote(payload, email)

        return email
    }

    fun getOrCreateUser(email: String): User {
        return userRepository.findByEmail(email)
            ?: userRepository.save(
                User(email).apply {

                }
            )
    }
}