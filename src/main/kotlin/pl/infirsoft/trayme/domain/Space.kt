package pl.infirsoft.trayme.domain

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import pl.infirsoft.trayme.dto.SpaceDto
import java.time.LocalDateTime

@Entity
@Table(name = "space")
class Space(
    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    val module: Module,
    private var name: String,
    private var shareKey: String?,
    @OneToMany(mappedBy = "space", cascade = [CascadeType.REMOVE])
    @JsonManagedReference
    val contents: List<Content> = mutableListOf(),
    @OneToMany(mappedBy = "space", cascade = [CascadeType.ALL])
    private val userSpaces: List<UserSpace> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    private var shareKeyExpiredAt: LocalDateTime? = null

    fun toDto(user: User): SpaceDto {
        val userRole = userSpaces.find { it.getUser() == user }?.getRole()
        return SpaceDto(id!!, name, module.getModule(), userRole)
    }

    fun setName(newName: String) {
        this.name = newName
    }

    fun getName(): String {
        return name
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
