package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.Note
import pl.infirsoft.trayme.domain.QNote
import pl.infirsoft.trayme.domain.QSpace
import pl.infirsoft.trayme.domain.QUser
import pl.infirsoft.trayme.exception.NoteNotFoundException

class NoteCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : NoteCustomRepository {

    override fun findBy(id: Int): Note? {
        val root = QNote.note
        return queryFactory.select(root).from(root).where(root.id.eq(id)).fetchFirst()
    }

    override fun requireBy(id: Int): Note {
        return findBy(id) ?: throw NoteNotFoundException(id)
    }

    override fun findNotesByUserPassword(userPassword: String): List<Note> {
        val note = QNote.note
        val space = QSpace.space
        val user = QUser.user

        return queryFactory.select(note)
            .from(note)
            .join(space).on(space.content.id.eq(note.id))
            .join(user).on(user.id.eq(space.user.id))
            .where(user.password.eq(userPassword))
            .fetch()
    }
}
