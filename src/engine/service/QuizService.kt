package engine.service

import engine.domain.Quiz
import engine.persistence.QuizRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuizService(private val quizRepository: QuizRepository) {

    fun getQuizById(id: Long): Quiz? = quizRepository.findById(id)


    fun getAllQuizzes(): List<Quiz> = quizRepository.findAll()

    @Transactional
    fun createQuiz(title: String, text: String, options: List<String>, answer: List<Int>, authorId: Long): Quiz {
        val quiz = Quiz(title = title, text = text, options = options, answer = answer, authorId = authorId)
        return quizRepository.save(quiz)
    }

}