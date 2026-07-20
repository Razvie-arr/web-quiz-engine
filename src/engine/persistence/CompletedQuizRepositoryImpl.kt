package engine.persistence

import engine.domain.CompletedQuiz
import engine.mapper.toDomain
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

}