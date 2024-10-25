package pl.infirsoft.trayme.domain

import jakarta.persistence.*

@Entity
@Table(name = "content")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "content_type", discriminatorType = DiscriminatorType.STRING)
abstract class Content(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Int? = null
) {
    abstract fun toDto(): Any
}
