package engine.service

import engine.domain.CompletedQuiz
import engine.persistence.CompletedQuizRepository
import engine.persistence.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CompletedQuizService(
    private val completedQuizRepository: CompletedQuizRepository,
    private val userRepository: UserRepository
) {

    fun getUserCompletedQuizzes(pageable: Pageable, userEmail: String): Page<CompletedQuiz> {
        val userId = userRepository.findByEmail(userEmail)?.id ?: throw IllegalStateException("User should exist.")
        return completedQuizRepository.findAllByUserId(userId, pageable)
    }

    // fun createCompletedQuiz()

}