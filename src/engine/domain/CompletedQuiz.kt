package engine.domain

import kotlin.time.Instant

data class CompletedQuiz(val id: Long? = null, val userId: Long, val quizId: Long, val completedAt: Instant)
