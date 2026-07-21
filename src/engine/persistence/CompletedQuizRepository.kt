package engine.persistence

import engine.domain.CompletedQuiz
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CompletedQuizRepository {

    fun findAllByUserId(userId: Long, pageable: Pageable): Page<CompletedQuiz>

    fun create(completedQuiz: CompletedQuiz): CompletedQuiz

}