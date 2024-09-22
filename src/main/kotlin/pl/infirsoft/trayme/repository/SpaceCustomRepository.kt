package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Space

interface SpaceCustomRepository {
    fun getEntitiesBy(id: Int): List<*>
    fun requireBy(id: Int): Space
    fun findBy(id: Int): Space?

}