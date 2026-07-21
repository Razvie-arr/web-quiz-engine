package engine.persistence

import engine.domain.CompletedQuiz
import engine.mapper.toDomain
import engine.mapper.toEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class CompletedQuizRepositoryImpl(private val jpaCompletedQuizRepository: JpaCompletedQuizRepository) :
    CompletedQuizRepository {

    override fun findAllByUserId(
        userId: Long,
        pageable: Pageable
    ): Page<CompletedQuiz> = jpaCompletedQuizRepository.findAllByUserId(userId, pageable).map { it.toDomain() }

    override fun create(completedQuiz: CompletedQuiz): CompletedQuiz =
        jpaCompletedQuizRepository.save(completedQuiz.toEntity()).toDomain()

}