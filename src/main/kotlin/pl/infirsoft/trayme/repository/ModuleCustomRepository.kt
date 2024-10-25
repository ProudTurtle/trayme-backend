package pl.infirsoft.trayme.repository

import pl.infirsoft.trayme.domain.Module

interface ModuleCustomRepository {
    fun requireBy(id: Int): Module
    fun findBy(id: Int): Module?
}