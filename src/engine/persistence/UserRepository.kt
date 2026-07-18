package engine.persistence

import engine.domain.User

interface UserRepository {

    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
    fun findById(id: Long): User?
    fun save(user: User): User

}