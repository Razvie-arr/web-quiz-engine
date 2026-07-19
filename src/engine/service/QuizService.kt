package engine.service

import engine.domain.Quiz
import engine.exception.QuizNotFoundException
import engine.exception.QuizOwnershipException
import engine.persistence.QuizRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuizService(private val quizRepository: QuizRepository, private val userService: UserService) {

    fun getQuizById(id: Long): Quiz? = quizRepository.findById(id)


    fun getAllQuizzes(): List<Quiz> = quizRepository.findAll()

    @Transactional
    fun createQuiz(title: String, text: String, options: List<String>, answer: List<Int>, email: String): Quiz {
        val user = userService.findUserByEmail(email) ?: throw IllegalStateException("User should exist.")
        val quiz =
            Quiz(title = title, text = text, options = options, answer = answer, authorId = requireNotNull(user.id))
        return quizRepository.create(quiz)
    }

    @Transactional
    fun updateQuiz(
        quizId: Long,
        title: String,
        text: String,
        options: List<String>,
        answer: List<Int>,
        email: String,
    ): Quiz {
        val quiz = getQuizById(quizId) ?: throw QuizNotFoundException(quizId)
        verifyOwnership(quiz, email)
        val updatedQuiz =
            Quiz(id = quizId, title = title, text = text, options = options, answer = answer, authorId = quiz.authorId)
        return quizRepository.update(updatedQuiz)
    }

    @Transactional
    fun deleteQuizAsUser(quizId: Long, email: String) {
        val quiz = getQuizById(quizId) ?: throw QuizNotFoundException(quizId)
        verifyOwnership(quiz, email)
        quizRepository.deleteById(quizId)
    }

    private fun verifyOwnership(quiz: Quiz, email: String) {
        val user = userService.findUserByEmail(email) ?: throw IllegalStateException("User should exist.")
        if (quiz.authorId != user.id) {
            throw QuizOwnershipException(requireNotNull(quiz.id), email)
        }
    }


}