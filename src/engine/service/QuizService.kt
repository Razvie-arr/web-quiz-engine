package engine.service

import engine.domain.Quiz
import engine.entity.QuizEntity
import engine.mapper.toDomain
import engine.repository.QuizRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuizService(private val quizRepository: QuizRepository) {

    fun getQuizById(id: Long): Quiz? = quizRepository.findByIdOrNull(id)?.toDomain()


    fun getAllQuizzes(): List<Quiz> = quizRepository.findAll().map { it.toDomain() }

    fun createQuiz(title: String, text: String, options: List<String>, answer: List<Int>): Quiz {
        val quizEntity = QuizEntity(
            title = title,
            text = text,
            options = options,
            answer = answer
        )
        return quizRepository.save(quizEntity).toDomain()
    }

    fun checkAnswer(quiz: Quiz, answer: List<Int>) = quiz.answer.sorted() == answer.sorted()

}