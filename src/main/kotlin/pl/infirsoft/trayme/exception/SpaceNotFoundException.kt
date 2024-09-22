package pl.infirsoft.trayme.exception

class SpaceNotFoundException(id: Int) : RuntimeException("Space with id $id not found")