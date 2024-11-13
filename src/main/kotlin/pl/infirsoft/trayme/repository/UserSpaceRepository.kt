package pl.infirsoft.trayme.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.infirsoft.trayme.domain.UserSpace

interface UserSpaceRepository : JpaRepository<UserSpace, Int>