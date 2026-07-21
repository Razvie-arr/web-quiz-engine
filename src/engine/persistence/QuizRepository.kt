package engine.persistence

import engine.domain.Quiz
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface QuizRepository {

    fun findById(id: Long): Quiz?
    fun findAll(pageable: Pageable): Page<Quiz>
    fun deleteById(id: Long)
    fun create(quiz: Quiz): Quiz
    fun update(quiz: Quiz): Quiz

}