package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Recommendation

interface RecommendationCustomRepository {
    fun requireBy(email: String, id: Int): Recommendation
    fun findBy(email: String, id: Int): Recommendation?
    fun findRecommendationByUserEmail(email: String): List<Recommendation>
}