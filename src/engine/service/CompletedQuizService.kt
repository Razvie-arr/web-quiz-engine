package engine.service

import engine.domain.CompletedQuiz
import engine.persistence.CompletedQuizRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CompletedQuizService(
    private val completedQuizRepository: CompletedQuizRepository,
    private val userService: UserService
) {

    fun getUserCompletedQuizzes(pageable: Pageable, userEmail: String): Page<CompletedQuiz> {
        val userId = userService.findUserByEmail(userEmail)?.id ?: throw IllegalStateException("User should exist.")
        return completedQuizRepository.findAllByUserId(userId, pageable)
    }

    fun createCompletedQuiz(quizId: Long, userId: Long): CompletedQuiz {
        val newCompletedQuiz = CompletedQuiz(quizId = quizId, userId = userId, completedAt = LocalDateTime.now())
        return completedQuizRepository.create(newCompletedQuiz)
    }

}