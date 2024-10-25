package pl.infirsoft.trayme.domain

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
    private val password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Int = 0
}
