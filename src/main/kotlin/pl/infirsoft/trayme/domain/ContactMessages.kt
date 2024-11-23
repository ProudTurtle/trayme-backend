package pl.infirsoft.trayme.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "contact_messages")
class ContactMessages(
    private val email: String,
    private val topic: String,
    private val message: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0

    private var created_at: LocalDateTime? = null

    fun setCreatedAt(createdAt: LocalDateTime) {
        this.created_at = createdAt
    }
}
