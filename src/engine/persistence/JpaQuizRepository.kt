package engine.persistence

import engine.domain.Quiz
import engine.mapper.toDomain
import engine.mapper.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class JpaQuizRepository(private val springDataQuizRepository: SpringDataQuizRepository) : QuizRepository {

    override fun findById(id: Long) = springDataQuizRepository.findByIdOrNull(id)?.toDomain()
    override fun findByAuthorId(authorId: Long) = springDataQuizRepository.findByAuthorId(authorId)?.toDomain()
    override fun findAll() = springDataQuizRepository.findAll().map { it.toDomain() }
    override fun deleteById(id: Long) = springDataQuizRepository.deleteById(id)
    override fun save(quiz: Quiz): Quiz = springDataQuizRepository.save(quiz.toEntity()).toDomain()

}