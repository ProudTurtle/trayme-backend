package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.QUser
import pl.infirsoft.trayme.domain.User
import pl.infirsoft.trayme.exception.UserNotFoundException

class UserCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : UserCustomRepository {

    override fun requireBy(email: String): User {
        return findBy(email) ?: throw UserNotFoundException(email)
    }

    override fun findBy(email: String): User? {
        val root = QUser.user

        return queryFactory
            .selectFrom(root)
            .where(root.email.eq(email))
            .fetchOne()
    }
}
