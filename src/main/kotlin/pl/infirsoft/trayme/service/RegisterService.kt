package pl.infirsoft.trayme.service

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
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
    private val spaceService: SpaceService,
    @Value("\${google.oauth.client-id}") private val clientId: String,
    @Value("\${google.oauth.client-secret}") private val clientSecret: String,
    @Value("\${google.oauth.redirect-uri}") private val redirectUri: String
) {
    @Transactional
    fun generatePassword(userPassword: String?): RegisterDto {
        val existingUser = userPassword?.let {
            runCatching { userRepository.requireBy(it) }.getOrNull()
        }

        if (existingUser != null) {
            val spaces = spaceService.getAllSpaces(existingUser.getPassword())
            val spacesDTO = spaces.map { it.toDto(existingUser) }
            return RegisterDto(
                existingUser.getPassword(),
                existingUser.name,
                existingUser.email,
                existingUser.avatarUrl,
                spacesDTO
            )
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
        return RegisterDto(password, user.name, user.email, user.avatarUrl, spacesDTO)
    }

    fun registerUserFromGoogle(userPassword: String, code: String): RegisterDto {
        val accessToken = exchangeAuthorizationCodeForToken(code)
        val userInfo = getUserInfoFromToken(accessToken)

        val existingUser = userRepository.findByEmail(userInfo.email)
        val user = existingUser ?: userRepository.requireBy(userPassword).apply {
            name = userInfo.name
            email = userInfo.email
            avatarUrl = userInfo.avatarUrl
        }

        if (existingUser == null) {
            userRepository.save(user)
        }

        val spaces = spaceService.getAllSpaces(user.getPassword())
        val spacesDTO = spaces.map { it.toDto(user) }

        return RegisterDto(
            user.getPassword(),
            user.name,
            user.email,
            user.avatarUrl,
            spacesDTO
        )
    }

    fun exchangeAuthorizationCodeForToken(authCode: String): String {
        val tokenUrl = "https://oauth2.googleapis.com/token"
        val params = mapOf(
            "code" to authCode,
            "client_id" to clientId,
            "client_secret" to clientSecret,
            "redirect_uri" to redirectUri,
            "grant_type" to "authorization_code"
        )

        val restTemplate = RestTemplate()
        val response = restTemplate.postForEntity(tokenUrl, params, Map::class.java)

        return response.body?.get("access_token") as String
    }

    fun getUserInfoFromToken(token: String): UserInfo {
        val url = "https://www.googleapis.com/oauth2/v3/userinfo"
        val headers = org.springframework.http.HttpHeaders()
        headers.setBearerAuth(token)

        val entity = HttpEntity<String>(headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange(url, HttpMethod.GET, entity, Map::class.java)

        val body = response.body ?: throw IllegalStateException("Response body is null")

        return UserInfo(
            email = body["email"] as? String ?: throw IllegalStateException("Email not found"),
            avatarUrl = body["picture"] as? String ?: throw IllegalStateException("Avatar URL not found"),
            name = body["name"] as? String ?: throw IllegalStateException("Name not found")
        )
    }

    data class UserInfo(
        val email: String,
        val avatarUrl: String,
        val name: String
    )

}