package pl.infirsoft.trayme.domain;

import jakarta.persistence.Entity
import jakarta.persistence.Table
import pl.infirsoft.trayme.dto.RecommendationDto

@Entity
@Table(name = "recommendation")
class Recommendation(
    private var name: String,
    private var who: String,
    private var type: String,
    space: Space
) : Content(space = space) {
    override fun toDto(): RecommendationDto {
        return RecommendationDto(name, who, type)
    }

    fun setName(newName: String) {
        this.name = newName
    }

    fun setWho(newWho: String) {
        this.who = newWho
    }

    fun setType(newType: String) {
        this.type = newType
    }
}
