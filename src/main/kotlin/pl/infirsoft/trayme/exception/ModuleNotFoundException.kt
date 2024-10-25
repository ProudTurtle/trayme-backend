package pl.infirsoft.trayme.exception

class ModuleNotFoundException(id: Int) : RuntimeException("User with id $id not found")