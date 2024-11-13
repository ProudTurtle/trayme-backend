package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.*

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

}
