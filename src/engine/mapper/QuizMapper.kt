package engine.mapper

import engine.domain.Quiz
import engine.dto.QuizResponse
import engine.entity.QuizEntity

fun QuizEntity.toDomain(): Quiz = Quiz(
    id = requireNotNull(this.id),
    title = this.title,
    text = this.text,
    options = this.options.toList(),
    answer = this.answer.toList()
)

fun Quiz.toEntity() = QuizEntity(
    title = this.title,
    text = this.text,
    options = this.options,
    answer = this.answer
)

fun Quiz.toResponse() = QuizResponse(
    id = requireNotNull(this.id),
    title = this.title,
    text = this.text,
    options = this.options
)
