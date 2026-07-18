package engine.persistence

import engine.domain.User
import engine.mapper.toDomain
import engine.mapper.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class JpaUserRepository(private val springDataUserRepository: SpringDataUserRepository) : UserRepository {

    override fun findById(id: Long) = springDataUserRepository.findByIdOrNull(id)?.toDomain()
    override fun findByEmail(email: String) = springDataUserRepository.findByEmail(email)?.toDomain()
    override fun existsByEmail(email: String) = springDataUserRepository.existsByEmail(email)
    override fun save(user: User) = springDataUserRepository.save(user.toEntity()).toDomain()

}