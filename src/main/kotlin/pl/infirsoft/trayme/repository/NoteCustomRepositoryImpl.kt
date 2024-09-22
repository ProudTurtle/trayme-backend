package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.Note
import pl.infirsoft.trayme.domain.QNote

class NoteCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : NoteCustomRepository {

    override fun findBy(id: Int): List<Note> {
        val root = QNote.note

        return queryFactory.select(root).from(root).where(root.space.id.eq(id)).fetch()
    }
}
