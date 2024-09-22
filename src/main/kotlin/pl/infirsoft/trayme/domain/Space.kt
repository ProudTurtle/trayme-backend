package pl.infirsoft.trayme.domain

import jakarta.persistence.*
import pl.infirsoft.trayme.dto.SpaceDto

@Entity
@Table(name = "space")
 class Space(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    private val module: String,
    private val title: String,
    private val icon: String
){
    fun toDto(): SpaceDto {
        return SpaceDto(id, module, title, icon)
    }
}
