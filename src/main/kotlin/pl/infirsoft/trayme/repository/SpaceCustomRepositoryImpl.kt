package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.QSpace
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.exception.SpaceNotFoundException

class SpaceCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : SpaceCustomRepository {

    override fun getEntitiesBy(id: Int) {
        val root = QSpace.space

        val spaceType = queryFactory
            .select(root.module)
            .from(root)
            .where(root.id.eq(id))
            .fetchOne()

//        val results = when (spaceType) {
//            "notes" -> queryFactory
//                .selectFrom(QNote.note)
//                .fetch().map(Note::toDto)
//
//            "recommendations" -> queryFactory
//                .selectFrom(QRecommendation.recommendation)
//                .fetch().map(Recommendation::toDto)
//
//            else -> emptyList()
//        }
//        return results
    }

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

        return queryFactory.selectFrom(root).where(root.user.password.eq(userPassword)).fetch()
    }

    override fun findByContentAndUserPassword(contentId: Int, userPassword: String): Space? {
        val root = QSpace.space

        return queryFactory.selectFrom(root)
            .where(root.content.id.eq(contentId).and(root.user.password.eq(userPassword))).fetchOne()
    }

    override fun requireByContentIdAnsUserPassword(contentId: Int, userPassword: String): Space {
        return findByContentAndUserPassword(contentId, userPassword) ?: throw SpaceNotFoundException(contentId)
    }
}
