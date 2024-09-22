package pl.infirsoft.trayme.exception

class NoteNotFoundException(id: Int) : RuntimeException("Note with id $id not found")