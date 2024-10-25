package pl.infirsoft.trayme.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.infirsoft.trayme.domain.Module

interface ModuleRepository : JpaRepository<Module, Int>, ModuleCustomRepository