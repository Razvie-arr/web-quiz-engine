package engine.repository

import engine.entity.QuizEntity
import org.springframework.data.repository.CrudRepository

interface SpringDataQuizRepository : CrudRepository<QuizEntity, Long>