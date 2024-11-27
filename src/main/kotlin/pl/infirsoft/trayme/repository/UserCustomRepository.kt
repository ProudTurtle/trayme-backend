package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.User

interface UserCustomRepository {
    fun requireBy(email: String): User
    fun findBy(email: String): User?
}