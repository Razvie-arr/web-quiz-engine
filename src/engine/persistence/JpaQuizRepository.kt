package engine.persistence

import engine.domain.Quiz
import engine.mapper.toDomain
import engine.mapper.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class JpaQuizRepository(
    private val springDataQuizRepository: SpringDataQuizRepository,
    private val springDataUserRepository: SpringDataUserRepository
) : QuizRepository {

    override fun findById(id: Long) = springDataQuizRepository.findByIdOrNull(id)?.toDomain()
    override fun findByAuthorId(authorId: Long) = springDataQuizRepository.findByAuthorId(authorId)?.toDomain()
    override fun findAll() = springDataQuizRepository.findAll().map { it.toDomain() }
    override fun deleteById(id: Long) = springDataQuizRepository.deleteById(id)
    override fun create(quiz: Quiz): Quiz {
        val quizEntity = quiz.toEntity()
        quizEntity.author =
            springDataUserRepository.findByIdOrNull(quiz.authorId) ?: throw IllegalStateException("User should exist.")
        return springDataQuizRepository.save(quizEntity).toDomain()
    }

    override fun update(quiz: Quiz): Quiz {
        val quizEntity =
            springDataQuizRepository.findByIdOrNull(requireNotNull(quiz.id))
                ?: throw IllegalStateException("Quiz should exist.")

        quizEntity.title = quiz.title
        quizEntity.text = quiz.text

        quizEntity.options.clear()
        quizEntity.options.addAll(quiz.options)

        quizEntity.answer.clear()
        quizEntity.answer.addAll(quiz.answer)
        
        return springDataQuizRepository.save(quizEntity).toDomain()
    }

}