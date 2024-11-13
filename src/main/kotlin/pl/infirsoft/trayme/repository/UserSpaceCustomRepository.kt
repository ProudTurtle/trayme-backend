package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.domain.User

interface UserSpaceCustomRepository {
    fun checkIfUserSpaceExist(user: User, space: Space): Boolean
}