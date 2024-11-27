package pl.infirsoft.trayme.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import pl.infirsoft.trayme.domain.*
import pl.infirsoft.trayme.exception.NoteNotFoundException

class NoteCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : NoteCustomRepository {
    override fun requireBy(email: String, id: Int): Note {
        return findNoteByUserEmailAndNoteId(email, id) ?: throw NoteNotFoundException(id)
    }

    override fun findNotesByUserEmailAndSpaceId(email: String, spaceId: Int): List<Note> {
        val note = QNote.note
        val content = QContent.content
        val space = QSpace.space
        val user = QUser.user
        val userSpace = QUserSpace.userSpace

        return queryFactory
            .select(note)
            .from(note)
            .join(content).on(note.id.eq(content.id))
            .join(space).on(space.id.eq(content.space.id))
            .join(userSpace).on(userSpace.space.id.eq(space.id))
            .join(user).on(user.id.eq(userSpace.user.id))
            .where(user.email.eq(email).and(space.id.eq(spaceId)))
            .fetch()
    }

    override fun findNoteByUserEmailAndNoteId(email: String, noteId: Int): Note? {
        val note = QNote.note
        val content = QContent.content
        val space = QSpace.space
        val user = QUser.user
        val userSpace = QUserSpace.userSpace

        return queryFactory
            .select(note)
            .from(note)
            .join(content).on(note.id.eq(content.id))
            .join(space).on(space.id.eq(content.space.id))
            .join(userSpace).on(userSpace.space.id.eq(space.id))
            .join(user).on(user.id.eq(userSpace.user.id))
            .where(user.email.eq(email).and(note.id.eq(noteId)))
            .fetchFirst()
    }
}
