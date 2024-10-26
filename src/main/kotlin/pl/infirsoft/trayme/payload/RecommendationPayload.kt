package pl.infirsoft.trayme.payload;

import pl.infirsoft.trayme.domain.Recommendation
import pl.infirsoft.trayme.domain.Space

data class RecommendationPayload(
    val name: String,
    val who: String,
    val type: String,
    val spaceId: Int
) {
    fun toEntity(space: Space): Recommendation {
        return Recommendation(name, who, type, space)
    }
}

data class RecommendationUpdatePayload(
    val name: String,
    val who: String,
    val type: String
)
