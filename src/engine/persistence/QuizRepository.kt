package engine.persistence

import engine.domain.Quiz

interface QuizRepository {

    fun findById(id: Long): Quiz?
    fun findAll(): List<Quiz>
    fun save(quiz: Quiz): Quiz

}