package pl.infirsoft.trayme.aspect

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import pl.infirsoft.trayme.repository.UserRepository
import java.util.*

@Aspect
@Component
class UserVerificationAspect(val userRepository: UserRepository) {

    companion object {
        private val userEmailContext = ThreadLocal<String>()
        fun getUserEmail(): String = userEmailContext.get()
            ?: throw IllegalStateException("No user email found in context")
    }

    @Before("@annotation(RequiresUser)")
    fun verifyUser() {
        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val authHeader = request.getHeader("Authorization")
            ?: throw IllegalArgumentException("Missing Authorization header")

        if (!authHeader.startsWith("Bearer ")) {
            throw IllegalArgumentException("Invalid Authorization header format")
        }

        val token = authHeader.substringAfter("Bearer ")
        val decodedToken = verifyGoogleToken(token)
        val email = decodedToken.payload.email

        println("User email: $email")
        userEmailContext.set(email)
    }

    @After("@annotation(RequiresUser)")
    fun clearContext() {
        userEmailContext.remove()
    }

    private fun verifyGoogleToken(idToken: String): GoogleIdToken {
        val transport: HttpTransport = NetHttpTransport()
        val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()

        val verifier = GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            .setAudience(Collections.singletonList("UZUPELNIJ"))
            .build()

        return verifier.verify(idToken)
            ?: throw IllegalArgumentException("Invalid ID Token")
    }
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresUser