package pl.infirsoft.trayme.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import pl.infirsoft.trayme.aspect.RequiresUser
import pl.infirsoft.trayme.aspect.UserVerificationAspect
import pl.infirsoft.trayme.domain.Recommendation
import pl.infirsoft.trayme.dto.RecommendationDto
import pl.infirsoft.trayme.payload.RecommendationPayload
import pl.infirsoft.trayme.payload.RecommendationUpdatePayload
import pl.infirsoft.trayme.service.RecommendationService

@RestController
@RequestMapping("/recommendation")
class RecommendationController(
    private val recommendationService: RecommendationService
) {
    @RequiresUser
    @GetMapping
    @Operation(summary = "Rekomendacje.Lista", description = "Zwraca listę rekomendacji dla danego emaila")
    fun getNotes(): List<RecommendationDto> {
        return recommendationService.getRecommendations(UserVerificationAspect.getUserEmail())
            .map(Recommendation::toDto)
    }

    @RequiresUser
    @PostMapping
    @Operation(summary = "Rekomendacje.Tworzenie", description = "Tworzy nową rekomendacje")
    fun createNote(
        @RequestBody recommendationPayload: RecommendationPayload
    ): RecommendationDto {
        return recommendationService.createRecommendation(recommendationPayload, UserVerificationAspect.getUserEmail())
            .toDto()
    }

    @RequiresUser
    @PutMapping("{recommendationId}")
    @Operation(summary = "Rekomendacje.Aktualizacja", description = "Aktualizuje rekomendacje")
    fun updateNote(
        @RequestBody recommendationPayload: RecommendationUpdatePayload,
        @PathVariable noteId: Int
    ): RecommendationDto {
        return recommendationService.updateRecommendation(
            recommendationPayload,
            noteId,
            UserVerificationAspect.getUserEmail()
        ).toDto()
    }

    @RequiresUser
    @DeleteMapping("{recommendationId}")
    @Operation(summary = "Rekomendacje.Usuwanie", description = "Usuwa wskazaną rekomendacje")
    fun deleteNote(@PathVariable recommendationId: Int) {
        recommendationService.deleteRecommendation(recommendationId, UserVerificationAspect.getUserEmail())
    }
}
