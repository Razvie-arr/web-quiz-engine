package engine.mapper

import engine.domain.Quiz
import engine.dto.QuizResponse
import engine.entity.QuizEntity
import engine.entity.UserEntity

fun QuizEntity.toDomain(): Quiz = Quiz(
    id = requireNotNull(this.id) { "Quiz must have an id" },
    title = this.title,
    text = this.text,
    options = this.options.toList(),
    answer = this.answer.toList(),
    authorId = requireNotNull(this.author?.id) { "Quiz must have a valid author" }
)

fun Quiz.toEntity() = QuizEntity(
    title = this.title,
    text = this.text,
    options = this.options,
    answer = this.answer,
    author = UserEntity(id = this.authorId)
)

fun Quiz.toResponse() = QuizResponse(
    id = requireNotNull(this.id) { "Quiz must have an id" },
    title = this.title,
    text = this.text,
    options = this.options
)
