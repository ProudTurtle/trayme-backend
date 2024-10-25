package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Space

interface SpaceCustomRepository {
    fun getEntitiesBy(id: Int)
    fun requireBy(id: Int): Space
    fun findBy(id: Int): Space?
    fun findByUserPassword(userPassword: String): List<Space>

}