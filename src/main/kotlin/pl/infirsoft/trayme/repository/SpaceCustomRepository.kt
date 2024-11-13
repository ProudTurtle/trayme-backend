package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Space

interface SpaceCustomRepository {
    fun requireBy(id: Int): Space
    fun findBy(id: Int): Space?
    fun findByUserPassword(userPassword: String): List<Space>
    fun findByIdAndUserPassword(spaceId: Int, userPassword: String): Space?
    fun requireByIdIdAnsUserPassword(spaceId: Int, userPassword: String): Space
    fun findByShareKey(shareKey: String): Space?
    fun requireByShareKey(shareKey: String): Space
}