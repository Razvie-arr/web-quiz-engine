package engine.mapper

import engine.domain.Quiz
import engine.dto.QuizResponse
import engine.entity.QuizEntity

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
    options = this.options.toMutableList(),
    answer = this.answer.toMutableList(),
)

fun Quiz.toResponse() = QuizResponse(
    id = requireNotNull(this.id) { "Quiz must have an id" },
    title = this.title,
    text = this.text,
    options = this.options
)
