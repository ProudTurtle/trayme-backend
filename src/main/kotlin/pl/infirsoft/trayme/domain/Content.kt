package pl.infirsoft.trayme.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Content(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "space_id")
    @JsonBackReference
    open val space: Space
) {
    abstract fun toDto(): Any
}
