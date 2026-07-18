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
        return quizRepository.save(quiz)
    }

    @Transactional
    fun deleteQuizAsUser(quizId: Long, email: String) {
        val quiz = getQuizById(quizId) ?: throw QuizNotFoundException(quizId)
        val user = userService.findUserByEmail(email) ?: throw IllegalStateException("User should exist.")
        if (quiz.authorId != user.id) {
            throw QuizOwnershipException(quizId, email)
        }
        quizRepository.deleteById(quizId)
    }

}