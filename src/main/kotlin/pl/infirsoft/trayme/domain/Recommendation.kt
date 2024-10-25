package pl.infirsoft.trayme.domain;

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import pl.infirsoft.trayme.dto.RecommendationDto

@Entity
@DiscriminatorValue("recommendation")
class Recommendation(
    private var name: String,
    private var who: String,
    private var type: String
) : Content() {
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
