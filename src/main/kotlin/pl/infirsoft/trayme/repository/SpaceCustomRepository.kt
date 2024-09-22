package pl.infirsoft.trayme.repository

import org.springframework.stereotype.Repository
import pl.infirsoft.trayme.domain.Note

interface SpaceCustomRepository{
    fun getEntitiesBy(id: Int): List<*>
}