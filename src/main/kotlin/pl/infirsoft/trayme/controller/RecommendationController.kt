package pl.infirsoft.trayme.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import pl.infirsoft.trayme.domain.Recommendation
import pl.infirsoft.trayme.dto.RecommendationDto
import pl.infirsoft.trayme.payload.RecomenndationPayload
import pl.infirsoft.trayme.service.RecommendationService

@RestController
@RequestMapping("/recommendation")
class RecommendationController(
    private val recommendationService: RecommendationService
) {
    @GetMapping
    @Operation(summary = "Rekomendacje.Lista", description = "Zwraca listę rekomendacji dla danego userPassword")
    fun getNotes(@RequestHeader userPassword: String): List<RecommendationDto> {
        return recommendationService.getRecommendations(userPassword).map(Recommendation::toDto)
    }

    @PostMapping("/create")
    @Operation(summary = "Rekomendacje.Tworzenie", description = "Tworzy nową rekomendacje")
    fun createNote(
        @RequestHeader userPassword: String,
        @RequestBody recommendationPayload: RecomenndationPayload
    ): RecommendationDto {
        return recommendationService.createRecommendation(recommendationPayload, userPassword).toDto()
    }

    @PutMapping("{recommendationId}/update")
    @Operation(summary = "Rekomendacje.Aktualizacja", description = "Aktualizuje rekomendacje")
    fun updateNote(
        @RequestBody recommendationPayload: RecomenndationPayload,
        @PathVariable noteId: Int
    ): RecommendationDto {
        return recommendationService.updateRecommendation(recommendationPayload, noteId).toDto()
    }

    @DeleteMapping("{recommendationId}")
    @Operation(summary = "Rekomendacje.Usuwanie", description = "Usuwa wskazaną rekomendacje")
    fun deleteNote(@PathVariable recommendationId: Int, @RequestHeader userPassword: String) {
        recommendationService.deleteRecommendation(recommendationId, userPassword)
    }
}
