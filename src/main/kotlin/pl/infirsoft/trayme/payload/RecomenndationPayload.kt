package pl.infirsoft.trayme.payload;

import pl.infirsoft.trayme.domain.Recommendation

data class RecomenndationPayload(
    val name: String,
    val who: String,
    val type: String
) {
    fun toEntity(): Recommendation {
        return Recommendation(name, who, type)
    }
}
