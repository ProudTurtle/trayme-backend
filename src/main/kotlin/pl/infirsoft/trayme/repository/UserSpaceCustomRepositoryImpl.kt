package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.*
import pl.infirsoft.trayme.exception.UserSpaceNotFoundException

class UserSpaceCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : UserSpaceCustomRepository {
    override fun checkIfUserSpaceExist(user: User, space: Space): Boolean {
        val root = QUserSpace.userSpace

        return queryFactory.selectFrom(root)
            .where(
                root.user.id.eq(user.id)
                    .and(root.space.id.eq(space.id))
            )
            .fetchFirst() != null
    }

    override fun findBySpaceIdAndUserRole(userPassword: String, spaceId: Int): UserSpace? {
        val root = QUserSpace.userSpace

        return queryFactory.selectFrom(root)
            .where(
                root.space.id.eq(spaceId)
                    .and(root.user.password.eq(userPassword))
            ).fetchFirst()
    }

    override fun requireBySpaceIdAndUserRole(userPassword: String, spaceId: Int): UserSpace {
        return findBySpaceIdAndUserRole(userPassword, spaceId) ?: throw UserSpaceNotFoundException(spaceId)
    }
}
