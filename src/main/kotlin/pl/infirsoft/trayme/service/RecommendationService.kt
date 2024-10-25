package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.Recommendation
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.exception.ModuleNotFoundException
import pl.infirsoft.trayme.exception.NoteServiceException
import pl.infirsoft.trayme.exception.SpaceNotFoundException
import pl.infirsoft.trayme.exception.UserNotFoundException
import pl.infirsoft.trayme.payload.RecomenndationPayload
import pl.infirsoft.trayme.repository.ModuleRepository
import pl.infirsoft.trayme.repository.RecomendationRepository
import pl.infirsoft.trayme.repository.SpaceRepository
import pl.infirsoft.trayme.repository.UserRepository

@Service
class RecommendationService(
    private val recommendationRepository: RecomendationRepository,
    private val spaceRepository: SpaceRepository,
    private val userRepository: UserRepository,
    private val moduleRepository: ModuleRepository
) {

    fun createRecommendation(recommendationPayload: RecomenndationPayload, userPassword: String): Recommendation {
        return try {
            val user = userRepository.requireBy(userPassword)
            val module = moduleRepository.requireBy(6)
            val recommendation = recommendationRepository.save(recommendationPayload.toEntity())
            val space = Space(module, user, recommendation)
            spaceRepository.save(space)
            recommendation
        } catch (e: UserNotFoundException) {
            throw NoteServiceException("User not found", e)
        } catch (e: ModuleNotFoundException) {
            throw NoteServiceException("Module not found", e)
        }
    }

    fun getRecommendations(userPassword: String): List<Recommendation> {
        return recommendationRepository.findNotesByUserPassword(userPassword)
    }

    fun updateRecommendation(payload: RecomenndationPayload, noteId: Int): Recommendation {
        val recommendation = recommendationRepository.requireBy(noteId)

        payload.name.let { recommendation.setName(it) }
        payload.who.let { recommendation.setWho(it) }
        payload.type.let { recommendation.setType(it) }
        return recommendationRepository.save(recommendation)
    }

    fun deleteRecommendation(noteId: Int, userPassword: String) {
        try {
            val space = spaceRepository.requireByContentIdAnsUserPassword(noteId, userPassword)
            spaceRepository.delete(space)
        } catch (e: SpaceNotFoundException) {
            throw NoteServiceException("Space not found", e)
        }
    }
}