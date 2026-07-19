package engine.persistence

import engine.entity.QuizEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataQuizRepository : JpaRepository<QuizEntity, Long> {

}