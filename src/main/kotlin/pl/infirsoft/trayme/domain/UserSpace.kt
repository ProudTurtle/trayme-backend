package pl.infirsoft.trayme.domain

import jakarta.persistence.*

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

    fun getRole(): String {
        return role
    }

    fun getUser(): User {
        return user
    }
}
