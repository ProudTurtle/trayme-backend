package pl.infirsoft.trayme.domain;

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import pl.infirsoft.trayme.dto.RecommendationDto

@Entity
@DiscriminatorValue("recommendation")
class Recommendation(
    private val name: String,
    private val who: String,
    private val type: String
) : Content() {
    fun toDto(): RecommendationDto {
        return RecommendationDto(name, who, type)
    }
}
