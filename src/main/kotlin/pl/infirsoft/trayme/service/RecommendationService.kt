package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.Recommendation
import pl.infirsoft.trayme.exception.NoteServiceException
import pl.infirsoft.trayme.exception.SpaceNotFoundException
import pl.infirsoft.trayme.payload.RecommendationPayload
import pl.infirsoft.trayme.payload.RecommendationUpdatePayload
import pl.infirsoft.trayme.repository.RecommendationRepository
import pl.infirsoft.trayme.repository.SpaceRepository

@Service
class RecommendationService(
    private val recommendationRepository: RecommendationRepository,
    private val spaceRepository: SpaceRepository
) {

    fun createRecommendation(recommendationPayload: RecommendationPayload, email: String): Recommendation {
        return try {
            val space = spaceRepository.requireByIdIdAnsUserEmail(recommendationPayload.spaceId, email)
            val recommendation = recommendationRepository.save(recommendationPayload.toEntity(space))
            spaceRepository.save(space)
            recommendation
        } catch (e: SpaceNotFoundException) {
            throw NoteServiceException("Space not found", e)
        }
    }

    fun getRecommendations(email: String): List<Recommendation> {
        return recommendationRepository.findRecommendationByUserEmail(email)
    }

    fun updateRecommendation(
        payload: RecommendationUpdatePayload,
        recommendationId: Int,
        email: String
    ): Recommendation {
        val recommendation = recommendationRepository.requireBy(email, recommendationId)

        payload.name.let { recommendation.setName(it) }
        payload.who.let { recommendation.setWho(it) }
        payload.type.let { recommendation.setType(it) }
        return recommendationRepository.save(recommendation)
    }

    fun deleteRecommendation(recommendationId: Int, email: String) {
        try {
            val recommendation = recommendationRepository.requireBy(email, recommendationId)
            recommendationRepository.delete(recommendation)
        } catch (e: SpaceNotFoundException) {
            throw NoteServiceException("Recommendation not found", e)
        }
    }
}