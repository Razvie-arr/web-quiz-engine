package engine.service

import engine.domain.Quiz
import engine.repository.QuizRepository
import engine.util.QuizIdGenerator
import org.springframework.stereotype.Service

@Service
class QuizService(private val quizRepository: QuizRepository, private val idGenerator: QuizIdGenerator) {

    fun getQuizById(id: Int): Quiz? {
        return quizRepository.getQuizById(id)
    }

    fun getAllQuizzes(): List<Quiz> {
        return quizRepository.getAllQuizzes()
    }

    fun createQuiz(title: String, text: String, options: List<String>, answer: Int): Quiz {
        val newQuizId = idGenerator.generateId()
        val newQuiz = Quiz(newQuizId, title, text, options, answer)
        quizRepository.addQuiz(newQuiz)
        return newQuiz
    }

    fun checkAnswer(quiz: Quiz, answerIndex: Int): Boolean {
        return quiz.answer == answerIndex
    }

}