package engine.repository

import engine.domain.Quiz
import engine.mapper.toDomain
import engine.mapper.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class QuizRepository(private val springDataQuizRepository: SpringDataQuizRepository) {

    fun findById(id: Long) = springDataQuizRepository.findByIdOrNull(id)?.toDomain()

    fun findAll() = springDataQuizRepository.findAll().map { it.toDomain() }

    fun save(quiz: Quiz): Quiz = springDataQuizRepository.save(quiz.toEntity()).toDomain()

}