package pl.infirsoft.trayme.domain

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
    val password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0
}
