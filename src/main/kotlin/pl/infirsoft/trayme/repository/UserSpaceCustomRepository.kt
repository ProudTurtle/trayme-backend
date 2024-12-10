package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Space
import pl.infirsoft.trayme.domain.User
import pl.infirsoft.trayme.domain.UserSpace

interface UserSpaceCustomRepository {
    fun checkIfUserSpaceExist(user: User, space: Space): Boolean
    fun findBySpaceIdAndUserRole(userPassword: String, spaceId: Int): UserSpace?
    fun requireBySpaceIdAndUserRole(userPassword: String, spaceId: Int): UserSpace
}