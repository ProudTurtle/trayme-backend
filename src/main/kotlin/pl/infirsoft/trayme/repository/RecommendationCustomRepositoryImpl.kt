package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.QRecommendation
import pl.infirsoft.trayme.domain.QSpace
import pl.infirsoft.trayme.domain.QUser
import pl.infirsoft.trayme.domain.Recommendation
import pl.infirsoft.trayme.exception.NoteNotFoundException

class RecommendationCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : RecommendationCustomRepository {

    override fun findBy(id: Int): Recommendation? {
        val root = QRecommendation.recommendation
        return queryFactory.select(root).from(root).where(root.id.eq(id)).fetchFirst()
    }

    override fun requireBy(id: Int): Recommendation {
        return findBy(id) ?: throw NoteNotFoundException(id)
    }

    override fun findNotesByUserPassword(userPassword: String): List<Recommendation> {
        val note = QRecommendation.recommendation
        val space = QSpace.space
        val user = QUser.user

        return queryFactory.select(note)
            .from(note)
            .join(space).on(space.content.id.eq(note.id))
            .join(user).on(user.id.eq(space.user.id))
            .where(user.password.eq(userPassword))
            .fetch()
    }
}
