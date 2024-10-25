package pl.infirsoft.trayme.domain

import jakarta.persistence.*

@Entity
@Table(name = "space")
class Space(
    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private val module: Module,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private val user: User,
    @OneToOne
    @JoinColumn(name = "content_id")
    private val content: Content
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

//    fun toDto(): SpaceDto {
//        return SpaceDto(module, title, icon)
//    }
}
