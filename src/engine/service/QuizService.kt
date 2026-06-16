package engine.service

import engine.domain.QuizQuestion
import org.springframework.stereotype.Service

@Service
class QuizService {

    fun getQuizQuestion(): QuizQuestion {
        return QuizQuestion("Ultimate answer", "What's the answer to ultimate question of life, the Universe, and Everything?", listOf("777", "67", "42", "1337"), 2)
    }

    fun checkAnswer(quizQuestion: QuizQuestion, answerIndex: Int): Boolean {
        return quizQuestion.correctOptionIndex == answerIndex
    }

}