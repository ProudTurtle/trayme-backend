package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.QSpace
import pl.infirsoft.trayme.domain.QUser
import pl.infirsoft.trayme.domain.QUserSpace
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.exception.SpaceNotFoundException

class SpaceCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : SpaceCustomRepository {

    override fun requireBy(id: Int): Space {
        return findBy(id) ?: throw SpaceNotFoundException(id = id)
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
            .join(userSpace).on(userSpace.space.id.eq(root.id))
            .join(user).on(user.id.eq(userSpace.user.id))
            .where(user.password.eq(userPassword))
            .fetch()
    }


    override fun findByIdAndUserPassword(spaceId: Int, userPassword: String): Space? {
        val root = QSpace.space
        val user = QUser.user
        val userSpace = QUserSpace.userSpace

        return queryFactory.selectFrom(root)
            .join(userSpace).on(userSpace.space.id.eq(root.id))
            .join(user).on(user.id.eq(userSpace.user.id))
            .where(
                user.password.eq(userPassword)
                    .and(root.id.eq(spaceId))
            )
            .fetchOne()
    }

    override fun requireByIdIdAnsUserPassword(spaceId: Int, userPassword: String): Space {
        return findByIdAndUserPassword(spaceId, userPassword) ?: throw SpaceNotFoundException(spaceId)
    }

    override fun findByShareKey(shareKey: String): Space? {
        val root = QSpace.space

        return queryFactory.selectFrom(root).where(root.shareKey.eq(shareKey)).fetchFirst()
    }

    override fun requireByShareKey(shareKey: String): Space {
        return findByShareKey(shareKey) ?: throw SpaceNotFoundException(shareKey = shareKey)
    }
}
