package engine.persistence

import engine.domain.Quiz
import engine.mapper.toDomain
import engine.mapper.toEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class QuizRepositoryImpl(
    private val jpaQuizRepository: JpaQuizRepository,
    private val jpaUserRepository: JpaUserRepository
) : QuizRepository {

    override fun findById(id: Long) = jpaQuizRepository.findByIdOrNull(id)?.toDomain()
    override fun findAll(pageable: Pageable): Page<Quiz> =
        jpaQuizRepository.findAll(pageable).map { it.toDomain() }

    override fun deleteById(id: Long) = jpaQuizRepository.deleteById(id)
    override fun create(quiz: Quiz): Quiz {
        val quizEntity = quiz.toEntity()
        quizEntity.author =
            jpaUserRepository.findByIdOrNull(quiz.authorId) ?: throw IllegalStateException("User should exist.")
        return jpaQuizRepository.save(quizEntity).toDomain()
    }

    override fun update(quiz: Quiz): Quiz {
        val quizEntity =
            jpaQuizRepository.findByIdOrNull(requireNotNull(quiz.id))
                ?: throw IllegalStateException("Quiz should exist.")

        if (quizEntity.title != quiz.title) {
            quizEntity.title = quiz.title
        }

        if (quizEntity.text != quiz.text) {
            quizEntity.text = quiz.text
        }

        if (quizEntity.options.sorted() != quiz.options.sorted()) {
            quizEntity.options.clear()
            quizEntity.options.addAll(quiz.options)
        }

        if (quizEntity.answer.sorted() != quiz.answer.sorted()) {
            quizEntity.answer.clear()
            quizEntity.answer.addAll(quiz.answer)
        }

        return jpaQuizRepository.save(quizEntity).toDomain()
    }

}