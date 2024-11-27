package pl.infirsoft.trayme.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.infirsoft.trayme.domain.User

@Repository
interface UserRepository : JpaRepository<User, Int>, UserCustomRepository {
    fun findByEmail(email: String): User?
}