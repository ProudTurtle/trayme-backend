package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.User
import pl.infirsoft.trayme.repository.UserRepository
import java.security.SecureRandom

@Service
class RegisterService(
    private val userRepository: UserRepository
) {

    fun generatePassword(): String {
        val charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#\$%^&*()"
        val secureRandom = SecureRandom()


        val password = (5..12)
            .map { secureRandom.nextInt(charPool.length) }
            .map(charPool::get)
            .joinToString("")

        val user = User(password);

        userRepository.save(user)
        return password
    }
}