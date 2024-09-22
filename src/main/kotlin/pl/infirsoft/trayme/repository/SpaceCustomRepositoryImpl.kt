package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.*

class SpaceCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : SpaceCustomRepository {

    override fun getEntitiesBy(id: Int): List<*> {
        val root = QSpace.space

        val spaceType = queryFactory
            .select(root.module)
            .from(root)
            .where(root.id.eq(id))
            .fetchOne()

        val results = when (spaceType) {
            "notes" -> queryFactory
                .selectFrom(QNote.note)
                .fetch().map(Note::toDto)

            "recommendations" -> queryFactory
                .selectFrom(QRecommendation.recommendation)
                .fetch().map(Recommendation::toDto)

            else -> emptyList()
        }
        return results
    }


}
