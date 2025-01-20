package pl.infirsoft.trayme.domain

import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Content(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "space_id")
    open val space: Space
) {
    abstract fun toDto(): Any
}
