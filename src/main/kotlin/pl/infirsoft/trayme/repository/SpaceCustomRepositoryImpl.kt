package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.*
import pl.infirsoft.trayme.exception.SpaceNotFoundException

class SpaceCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : SpaceCustomRepository {

    override fun requireBy(id: Int): Space {
        return findBy(id) ?: throw SpaceNotFoundException(id)
    }

    override fun findBy(id: Int): Space? {
        val root = QSpace.space

        return queryFactory
            .selectFrom(root)
            .where(root.id.eq(id))
            .fetchOne()
    }

    override fun findByUserPassword(userPassword: String): List<Space> {
        val root = QSpace.space
        val user = QUser.user
        val userSpace = QUserSpace.userSpace

        return queryFactory.select(root)
            .from(root)
            .join(userSpace).on(user.id.eq(userSpace.user.id))
            .where(userSpace.user.password.eq(userPassword)).fetch()
    }

    override fun findByIdAndUserPassword(spaceId: Int, userPassword: String): Space? {
        val root = QSpace.space
        val user = QUser.user
        val userSpace = QUserSpace.userSpace

        return queryFactory.selectFrom(root)
            .join(userSpace).on(user.id.eq(userSpace.user.id))
            .where(userSpace.user.password.eq(userPassword).and(root.id.eq(spaceId))).fetchOne()
    }

    override fun requireByIdIdAnsUserPassword(spaceId: Int, userPassword: String): Space {
        return findByIdAndUserPassword(spaceId, userPassword) ?: throw SpaceNotFoundException(spaceId)
    }
}
