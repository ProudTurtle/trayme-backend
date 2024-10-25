package pl.infirsoft.trayme.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.infirsoft.trayme.domain.Recommendation

interface RecomendationRepository : JpaRepository<Recommendation, Int>, RecommendationCustomRepository