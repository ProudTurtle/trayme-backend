package pl.infirsoft.trayme.domain

import jakarta.persistence.*
import pl.infirsoft.trayme.dto.SpaceDto

@Entity
@Table(name = "user_space")
class UserSpace(
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private val user: User,
    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private val space: Space,
    private val role: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
}
