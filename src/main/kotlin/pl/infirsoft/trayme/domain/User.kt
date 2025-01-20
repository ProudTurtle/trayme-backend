package pl.infirsoft.trayme.domain

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
    private val password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0

    fun getPassword(): String {
        return password
    }

    var email: String = ""
    var name: String = ""
    var avatarUrl: String = ""
}
