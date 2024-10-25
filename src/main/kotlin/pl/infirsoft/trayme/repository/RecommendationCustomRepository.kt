package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Recommendation

interface RecommendationCustomRepository {
    fun requireBy(id: Int): Recommendation
    fun findBy(id: Int): Recommendation?
    fun findNotesByUserPassword(userPassword: String): List<Recommendation>
}