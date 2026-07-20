package engine.persistence

import engine.entity.CompletedQuizEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface JpaCompletedQuizRepository : JpaRepository<CompletedQuizEntity, Long> {

    fun findAllByUserId(userId: Long, pageable: Pageable): Page<CompletedQuizEntity>

}