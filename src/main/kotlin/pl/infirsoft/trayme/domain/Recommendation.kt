package pl.infirsoft.trayme.domain;

import jakarta.persistence.*
import pl.infirsoft.trayme.dto.RecommendationDto

@Entity
@Table(name = "recommendation")
class Recommendation(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    private val name: String,
    private val who: String,
    private val type: String
) {
    fun toDto(): RecommendationDto {
        return RecommendationDto(id, name, who, type)
    }
}
