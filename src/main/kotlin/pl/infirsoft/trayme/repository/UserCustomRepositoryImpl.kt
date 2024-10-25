package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.QUser
import pl.infirsoft.trayme.domain.User
import pl.infirsoft.trayme.exception.UserNotFoundException

class UserCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : UserCustomRepository {

    override fun requireBy(userPassword: String): User {
        return findBy(userPassword) ?: throw UserNotFoundException(userPassword)
    }

    override fun findBy(userPassword: String): User? {
        val root = QUser.user

        return queryFactory
            .selectFrom(root)
            .where(root.password.eq(userPassword))
            .fetchOne()
    }
}
