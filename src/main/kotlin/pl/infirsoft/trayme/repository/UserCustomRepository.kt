package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.User

interface UserCustomRepository {
    fun requireBy(userPassword: String): User
    fun findBy(userPassword: String): User?
}