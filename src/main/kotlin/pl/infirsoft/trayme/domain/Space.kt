package pl.infirsoft.trayme.domain

import jakarta.persistence.*
import pl.infirsoft.trayme.dto.SpaceDto
import java.time.LocalDateTime

@Entity
@Table(name = "space")
class Space(
    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    val module: Module,
    private var shareKey: String?,
    @OneToMany(mappedBy = "space", cascade = [CascadeType.REMOVE])
    val contents: List<Content> = mutableListOf(),
    @OneToMany(mappedBy = "space", cascade = [CascadeType.ALL])
    private val userSpaces: List<UserSpace> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0

    private var shareKeyExpiredAt: LocalDateTime? = null

    fun toDto(user: User): SpaceDto {
        val userRole = userSpaces.find { it.getUser() == user }?.getRole()
        val name = userSpaces.find { it.getUser() == user }?.getName()
        return SpaceDto(id, name, module.getModule(), userRole)
    }

    fun getName(): String? {
        return userSpaces.find { it.getRole() == "Owner" }?.getName()
    }

    fun setShareKeyExpiredAt(expiredAt: LocalDateTime) {
        this.shareKeyExpiredAt = expiredAt
    }

    fun setShareKey(shareKey: String) {
        this.shareKey = shareKey
    }

    fun getShareKeyExpiredAt(): LocalDateTime? {
        return shareKeyExpiredAt
    }

    fun getShareKey(): String? {
        return shareKey
    }
}
