package engine.persistence

import engine.domain.User
import engine.mapper.toDomain
import engine.mapper.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val jpaUserRepository: JpaUserRepository) : UserRepository {

    override fun findById(id: Long) = jpaUserRepository.findByIdOrNull(id)?.toDomain()
    override fun findByEmail(email: String) = jpaUserRepository.findByEmail(email)?.toDomain()
    override fun existsByEmail(email: String) = jpaUserRepository.existsByEmail(email)
    override fun create(user: User) = jpaUserRepository.save(user.toEntity()).toDomain()

}