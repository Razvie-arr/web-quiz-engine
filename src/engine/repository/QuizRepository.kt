package engine.repository

import engine.domain.Quiz
import org.springframework.stereotype.Repository

@Repository
class QuizRepository {

    private val quizzes = mutableListOf<Quiz>()

    fun getQuizById(id: Int): Quiz? {
        return quizzes.find { it.id == id }
    }

    fun getAllQuizzes(): List<Quiz> {
        return quizzes.toList()
    }

    fun addQuiz(quiz: Quiz) {
        quizzes.add(quiz)
    }

}