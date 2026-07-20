package engine.mapper

import engine.domain.CompletedQuiz
import engine.dto.CompletedQuizResponse
import engine.entity.CompletedQuizEntity

fun CompletedQuizEntity.toDomain() = CompletedQuiz(
    id = id,
    userId = requireNotNull(user?.id) { "CompletedQuiz #$id is missing a valid user_id" },
    quizId = requireNotNull(quiz?.id) { "CompletedQuiz #$id is missing a valid quiz_id" },
    completedAt = requireNotNull(completedAt) { "CompletedQuiz #$id is missing completedAt timestamp" }
)

fun CompletedQuiz.toResponse() = CompletedQuizResponse(
    id = requireNotNull(id) { "CompletedQuiz must have an id" },
    completedAt = completedAt
)