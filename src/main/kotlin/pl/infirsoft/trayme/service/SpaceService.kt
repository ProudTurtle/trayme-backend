package pl.infirsoft.trayme.service

import org.springframework.stereotype.Service
import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.repository.SpaceRepository

@Service
class SpaceService(private val repository: SpaceRepository) {

    fun getAllSpaces(userPassword: String): List<Space> {
        return repository.findAll()
    }

//    fun getByEntitiesBy(id: Int): List<*>{
//        return repository.getEntitiesBy(id)
//    }
}