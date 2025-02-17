package pl.infirsoft.trayme.aspect

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component
import pl.infirsoft.trayme.repository.UserRepository

@Aspect
@Component
class UserVerificationAspect(val userRepository: UserRepository) {

    @Before("@annotation(RequiresUser) && args(userPassword, ..)")
    fun verifyUser(userPassword: String, googleAuth: String) {
        userRepository.requireBy(userPassword)
    }

}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresUser