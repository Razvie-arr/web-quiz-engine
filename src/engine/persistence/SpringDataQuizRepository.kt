package engine.persistence

import engine.entity.QuizEntity
import org.springframework.data.repository.ListCrudRepository

interface SpringDataQuizRepository : ListCrudRepository<QuizEntity, Long>