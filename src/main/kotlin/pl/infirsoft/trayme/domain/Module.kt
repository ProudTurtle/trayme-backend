package pl.infirsoft.trayme.domain

import jakarta.persistence.*
import pl.infirsoft.trayme.dto.ModuleDto

@Entity
@Table(name = "module")
class Module(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    private val module: String,
    private val title: String,
    private val icon: String,
    private val description: String
) {
    fun toDto(): ModuleDto {
        return ModuleDto(id, module, title, icon, description)
    }

    fun getModule(): String {
        return module
    }
}
