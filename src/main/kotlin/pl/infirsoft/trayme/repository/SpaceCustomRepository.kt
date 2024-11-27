package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Space

interface SpaceCustomRepository {
    fun requireBy(id: Int): Space
    fun findBy(id: Int): Space?
    fun findByUserEmail(email: String): List<Space>
    fun findByIdAndUserEmail(spaceId: Int, email: String): Space?
    fun requireByIdIdAnsUserEmail(spaceId: Int, email: String): Space
    fun findByShareKey(shareKey: String): Space?
    fun requireByShareKey(shareKey: String): Space
}