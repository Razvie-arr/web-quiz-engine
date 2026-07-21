package engine.dto

import java.time.LocalDateTime

data class CompletedQuizResponse(
    val id: Long,
    val completedAt: LocalDateTime
)
