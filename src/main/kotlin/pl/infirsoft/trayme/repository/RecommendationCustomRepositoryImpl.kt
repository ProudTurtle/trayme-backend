package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.*
import pl.infirsoft.trayme.exception.NoteNotFoundException

class RecommendationCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : RecommendationCustomRepository {

    override fun findBy(userPassword: String, id: Int): Recommendation? {
        val root = QRecommendation.recommendation
        val content = QContent.content
        val space = QSpace.space
        val user = QUser.user
        val userSpace = QUserSpace.userSpace


        return queryFactory
            .select(root)
            .from(root)
            .join(content).on(root.id.eq(content.id))
            .join(space).on(space.id.eq(content.space.id))
            .join(userSpace).on(user.id.eq(userSpace.user.id))
            .where(user.password.eq(userPassword).and(root.id.eq(id)))
            .fetchFirst()
    }

    override fun requireBy(userPassword: String, id: Int): Recommendation {
        return findBy(userPassword, id) ?: throw NoteNotFoundException(id)
    }

    override fun findRecommendationByUserPassword(userPassword: String): List<Recommendation> {
        val recommendation = QRecommendation.recommendation
        val content = QContent.content
        val space = QSpace.space
        val user = QUser.user
        val userSpace = QUserSpace.userSpace

        return queryFactory
            .select(recommendation)
            .from(recommendation)
            .join(content).on(recommendation.id.eq(content.id))
            .join(space).on(space.id.eq(content.space.id))
            .join(userSpace).on(user.id.eq(userSpace.user.id))
            .where(user.password.eq(userPassword))
            .fetch()
    }
}
