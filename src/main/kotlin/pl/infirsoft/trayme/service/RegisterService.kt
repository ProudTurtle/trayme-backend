package pl.infirsoft.trayme.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.domain.User
import pl.infirsoft.trayme.payload.NotePayload
import pl.infirsoft.trayme.payload.SpacePayload
import pl.infirsoft.trayme.repository.ModuleRepository
import pl.infirsoft.trayme.repository.SpaceRepository
import pl.infirsoft.trayme.repository.UserRepository
import java.security.SecureRandom

@Service
class RegisterService(
    private val userRepository: UserRepository,
    private val noteService: NoteService,
    private val spaceService: SpaceService
) {
    @Transactional
    fun generatePassword(): String {
        val charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#\$%^&*()"
        val secureRandom = SecureRandom()

        val password = (5..12)
            .map { secureRandom.nextInt(charPool.length) }
            .map(charPool::get)
            .joinToString("")

        val user = User(password);

        userRepository.save(user)
        val spacePayload = SpacePayload("Notatki", 1)
        val space = spaceService.createSpace(spacePayload, password)
        val payload = NotePayload("Powitalna notatka", "Witaj w Trayme! :)", space.id!!)
        noteService.createNote(payload, password)

        return password
    }
}