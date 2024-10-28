package pl.infirsoft.trayme.domain

import jakarta.persistence.*
import pl.infirsoft.trayme.dto.SpaceDto

@Entity
@Table(name = "space")
class Space(
    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private val module: Module,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private val user: User,
    private var name: String,
    @OneToMany(mappedBy = "space", cascade = [CascadeType.REMOVE])
    val contents: List<Content> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    fun toDto(): SpaceDto {
        return SpaceDto(id!!,name, module.getModule())
    }

    fun setName(newName: String) {
        this.name = newName
    }
}
