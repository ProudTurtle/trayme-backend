package pl.infirsoft.trayme.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.infirsoft.trayme.domain.Space

@Repository
interface SpaceRepository : JpaRepository<Space, Int>, SpaceCustomRepository {
}