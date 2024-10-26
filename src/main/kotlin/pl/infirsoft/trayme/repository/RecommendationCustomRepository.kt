package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Recommendation

interface RecommendationCustomRepository {
    fun requireBy(userPassword: String, id: Int): Recommendation
    fun findBy(userPassword: String, id: Int): Recommendation?
    fun findRecommendationByUserPassword(userPassword: String): List<Recommendation>
}