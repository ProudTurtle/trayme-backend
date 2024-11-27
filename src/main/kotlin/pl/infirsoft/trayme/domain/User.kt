package pl.infirsoft.trayme.domain

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
    @Column(nullable = false, unique = true)
    val email: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0
}
