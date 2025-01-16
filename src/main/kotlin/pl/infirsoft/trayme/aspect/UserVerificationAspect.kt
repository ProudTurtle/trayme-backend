package pl.infirsoft.trayme.aspect

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import pl.infirsoft.trayme.repository.UserRepository
import java.net.http.HttpHeaders

@Aspect
@Component
class UserVerificationAspect(val userRepository: UserRepository) {

    @Before("@annotation(RequiresUser) && args(userPassword, googleAuth, ..)")
    fun verifyUser(userPassword: String, googleAuth: String) {
        val token = exchangeAuthorizationCodeForToken(googleAuth)
        val email = getEmailFromToken(token)
        println(email)
        userRepository.requireBy(userPassword)
    }

    fun exchangeAuthorizationCodeForToken(authCode: String): String {
        val tokenUrl = "https://oauth2.googleapis.com/token"
        val params = mapOf(
            "code" to authCode,
            "client_id" to "UZUPEŁNIJ",
            "client_secret" to "UZUPEŁNIJ",
            "redirect_uri" to "UZUPEŁNIJ",
            "grant_type" to "authorization_code"
        )

        val restTemplate = RestTemplate()
        val response = restTemplate.postForEntity(tokenUrl, params, Map::class.java)

        return response.body?.get("access_token") as String
    }


    fun getEmailFromToken(token: String): String {
        val url = "https://www.googleapis.com/oauth2/v3/userinfo"
        val headers = org.springframework.http.HttpHeaders()
        headers.setBearerAuth(token)

        val entity = HttpEntity<String>(headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange(url, HttpMethod.GET, entity, Map::class.java)

        return response.body?.get("email") as String
    }
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresUser