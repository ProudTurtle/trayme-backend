package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.Note
import pl.infirsoft.trayme.domain.QNote
import pl.infirsoft.trayme.exception.NoteNotFoundException

class NoteCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : NoteCustomRepository {

    override fun findBy(id: Int): Note? {
        val root = QNote.note
        return queryFactory.select(root).from(root).where(root.id.eq(id)).fetchFirst()
    }

    override fun requireBy(id: Int): Note {
        return findBy(id) ?: throw NoteNotFoundException(id)
    }
}
