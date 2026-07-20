package engine.persistence

import engine.entity.UserEntity
import org.springframework.data.repository.ListCrudRepository

interface JpaUserRepository : ListCrudRepository<UserEntity, Long> {

    fun findByEmail(email: String): UserEntity?
    fun existsByEmail(email: String): Boolean

}