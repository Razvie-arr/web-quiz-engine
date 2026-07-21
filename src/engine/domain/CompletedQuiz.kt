package engine.domain

import java.time.LocalDateTime

data class CompletedQuiz(val id: Long? = null, val userId: Long, val quizId: Long, val completedAt: LocalDateTime)
