package pl.infirsoft.trayme.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
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
    @GetMapping
    @Operation(summary = "Rekomendacje.Lista", description = "Zwraca listę rekomendacji dla danego userPassword")
    fun getNotes(@RequestHeader("X-User-Token") userPassword: String): List<RecommendationDto> {
        return recommendationService.getRecommendations(userPassword).map(Recommendation::toDto)
    }

    @PostMapping
    @Operation(summary = "Rekomendacje.Tworzenie", description = "Tworzy nową rekomendacje")
    fun createNote(
        @RequestHeader("X-User-Token") userPassword: String,
        @RequestBody recommendationPayload: RecommendationPayload
    ): RecommendationDto {
        return recommendationService.createRecommendation(recommendationPayload, userPassword).toDto()
    }

    @PutMapping("{recommendationId}")
    @Operation(summary = "Rekomendacje.Aktualizacja", description = "Aktualizuje rekomendacje")
    fun updateNote(
        @RequestHeader("X-User-Token") userPassword: String,
        @RequestBody recommendationPayload: RecommendationUpdatePayload,
        @PathVariable noteId: Int
    ): RecommendationDto {
        return recommendationService.updateRecommendation(recommendationPayload, noteId, userPassword).toDto()
    }

    @DeleteMapping("{recommendationId}")
    @Operation(summary = "Rekomendacje.Usuwanie", description = "Usuwa wskazaną rekomendacje")
    fun deleteNote(@PathVariable recommendationId: Int, @RequestHeader("X-User-Token") userPassword: String) {
        recommendationService.deleteRecommendation(recommendationId, userPassword)
    }
}
