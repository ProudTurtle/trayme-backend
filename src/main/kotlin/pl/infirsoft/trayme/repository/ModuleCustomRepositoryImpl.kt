package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.Module
import pl.infirsoft.trayme.domain.QModule
import pl.infirsoft.trayme.exception.ModuleNotFoundException

class ModuleCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : ModuleCustomRepository {

    override fun findBy(id: Int): Module? {
        val root = QModule.module1
        return queryFactory.select(root).from(root).where(root.id.eq(id)).fetchFirst()
    }

    override fun requireBy(id: Int): Module {
        return findBy(id) ?: throw ModuleNotFoundException(id)
    }
}
