package engine.persistence

import engine.domain.Quiz

interface QuizRepository {

    fun findById(id: Long): Quiz?
    fun findByAuthorId(authorId: Long): Quiz?
    fun findAll(): List<Quiz>
    fun save(quiz: Quiz): Quiz

}