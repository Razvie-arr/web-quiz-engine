package engine.dto

import kotlin.time.Instant

data class CompletedQuizResponse(val id: Long, val completedAt: Instant)
